package api.endpoints;
public class Routes {

            /*
        Swagger URL for User Models
        Create Post : https://petstore.swagger.io/v2/user
        Create Get: https://petstore.swagger.io/v2/user/{username}
        Create Put : https://petstore.swagger.io/v2/user/{username}
        Create Delete: https://petstore.swagger.io/v2/user/{username}
        */
    public static String base_url = "https://petstore.swagger.io/v2";

    //USER MODEL
    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";

    //Store MODEL

    //Pet MODEL

}
