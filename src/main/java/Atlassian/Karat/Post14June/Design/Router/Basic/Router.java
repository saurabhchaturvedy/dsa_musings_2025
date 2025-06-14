package Post14June.Design.Router.Basic;

import java.util.HashMap;
import java.util.Map;

public class Router {


    Map<String, String> pathResultMap = new HashMap<>();


    public Router() {

    }


    public void withRoute(String path, String result) {


        pathResultMap.put(path, result);

    }


    public String callRoute(String path) {

        return pathResultMap.get(path);
    }


    public static void main(String[] args) {


        Router router = new Router();


        router.withRoute("/foo", "test_data");
        router.withRoute("/bar", "best_data");
        router.withRoute("/koo", "cool");

        System.out.println(router.callRoute("/koo"));

    }
}
