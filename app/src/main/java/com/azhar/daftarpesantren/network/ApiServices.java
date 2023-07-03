package com.azhar.daftarpesantren.network;

/**
 * Created by Azhar Rivaldi on 07-03-2023
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

public class ApiServices {

    public static String BASEURL_PROV = "https://api-pesantren-indonesia.vercel.app/provinsi.json";
    public static String BASEURL_KAB = "https://api-pesantren-indonesia.vercel.app/kabupaten/{id_provinsi}.json";
    public static String BASEURL_PESANTREN = "https://api-pesantren-indonesia.vercel.app/pesantren/{id_kab_kota}.json";
}
