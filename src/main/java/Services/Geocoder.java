package Services;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageResponse;

public class Geocoder {
    public static String Geocode(String city) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(ConfigLoader.getApiKey("opencage_api_key"));

        JOpenCageForwardRequest request = new JOpenCageForwardRequest(city);
        request.setRestrictToCountryCode("ru"); // флаг для страны
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);

        if (response.getFirstPosition() != null) {
            var lat = response.getFirstPosition().getLat();
            var lng = response.getFirstPosition().getLng();
            return lat + ", " + lng;
        } else {
            return "Координаты не найдены для города: " + city;
        }
    }
}
