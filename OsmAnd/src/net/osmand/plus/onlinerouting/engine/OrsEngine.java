package net.osmand.plus.onlinerouting.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.osmand.Location;
import net.osmand.data.LatLon;
import net.osmand.plus.OsmandApplication;
import net.osmand.plus.R;
import net.osmand.plus.onlinerouting.EngineParameter;
import net.osmand.plus.onlinerouting.OnlineRoutingResponse;
import net.osmand.plus.onlinerouting.VehicleType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.osmand.util.Algorithms.isEmpty;

public class OrsEngine extends OnlineRoutingEngine {

	public OrsEngine(@Nullable Map<String, String> params) {
		super(params);
	}

	@NonNull
	@Override
	public EngineType getType() {
		return EngineType.ORS;
	}

	@NonNull
	@Override
	public String getStandardUrl() {
		return "https://api.openrouteservice.org/v2/directions/";
	}

	@Override
	protected void collectAllowedParameters() {
		allowParameters(EngineParameter.API_KEY);
	}

	@Override
	protected void collectAllowedVehicles(@NonNull List<VehicleType> vehicles) {
		vehicles.add(new VehicleType("driving-car", R.string.routing_engine_vehicle_type_car));
		vehicles.add(new VehicleType("driving-hgv", R.string.routing_engine_vehicle_type_hgv));
		vehicles.add(new VehicleType("cycling-regular", R.string.routing_engine_vehicle_type_cycling_regular));
		vehicles.add(new VehicleType("cycling-road", R.string.routing_engine_vehicle_type_cycling_road));
		vehicles.add(new VehicleType("cycling-mountain", R.string.routing_engine_vehicle_type_cycling_mountain));
		vehicles.add(new VehicleType("cycling-electric", R.string.routing_engine_vehicle_type_cycling_electric));
		vehicles.add(new VehicleType("foot-walking", R.string.routing_engine_vehicle_type_walking));
		vehicles.add(new VehicleType("foot-hiking", R.string.routing_engine_vehicle_type_hiking));
		vehicles.add(new VehicleType("wheelchair", R.string.routing_engine_vehicle_type_wheelchair));
	}

	@Override
	protected void makeFullUrl(@NonNull StringBuilder sb,
	                           @NonNull List<LatLon> path) {
		if (path.size() > 1) {
			String vehicleKey = get(EngineParameter.VEHICLE_KEY);
			if (!isEmpty(vehicleKey)) {
				sb.append(vehicleKey);
			}
			sb.append('?');
			String apiKey = get(EngineParameter.API_KEY);
			if (!isEmpty(apiKey)) {
				sb.append("api_key=").append(apiKey);
			}
			LatLon start = path.get(0);
			LatLon end = path.get(path.size() - 1);
			sb.append('&').append("start=")
					.append(start.getLongitude()).append(',').append(start.getLatitude());
			sb.append('&').append("end=")
					.append(end.getLongitude()).append(',').append(end.getLatitude());
		}
	}

	@Nullable
	@Override
	public OnlineRoutingResponse parseServerResponse(@NonNull String content,
	                                                 @NonNull OsmandApplication app,
	                                                 boolean leftSideNavigation) throws JSONException {
		JSONObject obj = new JSONObject(content);
		JSONArray array = obj.getJSONArray("features").getJSONObject(0)
				.getJSONObject("geometry").getJSONArray("coordinates");
		List<LatLon> points = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			JSONArray point = array.getJSONArray(i);
			double lon = Double.parseDouble(point.getString(0));
			double lat = Double.parseDouble(point.getString(1));
			points.add(new LatLon(lat, lon));
		}
		if (!isEmpty(points)) {
			List<Location> route = convertRouteToLocationsList(points);
			new OnlineRoutingResponse(route, null);
		}
		return null;
	}

	@Override
	public boolean parseServerMessage(@NonNull StringBuilder sb,
	                                  @NonNull String content) throws JSONException {
		JSONObject obj = new JSONObject(content);
		if (obj.has("error")) {
			String message = obj.getString("error");
			sb.append(message);
		}
		return obj.has("features");
	}
}
