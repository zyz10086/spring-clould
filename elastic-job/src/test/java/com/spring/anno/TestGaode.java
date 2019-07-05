package com.spring.anno;

public class TestGaode {

    public static void main(String[] args) {
        for(double t:gcj02_To_Bd09(116.419861,39.795106)){
            System.out.println(t);
        }
    }

    public static double[] gcj02_To_Bd09(double gg_lat, double gg_lon) {
        double pi = 3.1415926535897932384626;
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new double[]{bd_lat, bd_lon};
    }

}
