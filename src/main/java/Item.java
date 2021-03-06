import model.ItemList;
import model.ItemModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by walki on 5/24/2017.
 */
public class Item extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        char temp[] = request.getParameter("product").toCharArray();
        char copy[] = new char[temp.length - 2];
        for (int i = 0; i < temp.length - 2; ++i) {
            copy[i] = temp[i + 1];
        }

        final String generalName = String.valueOf(copy);
        final String productLocation = request.getParameter("image").replaceAll("[']", "");
        String itemName = "", cost = "", description = "";

        PrintWriter out = response.getWriter();
        //header
        Constants.header(out);

        //body]
        out.println("<div class=\"itemName\">");
        //insert selected item image and name

        //Setup to call Jersey API
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target(Constants.getBaseURI());
        String jsonResponse =
                target.path("v1").path("api").path("todos").path(generalName).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

        System.out.println(jsonResponse);
        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        //call the GET in API
        List<ItemList> todoList = objectMapper.readValue(jsonResponse, new TypeReference<List<ItemList>>() {
        });
        //set values

        for (ItemList todo : todoList) {
            if (todo.getLocation().equalsIgnoreCase(productLocation)) {

                itemName = todo.getItemName();
                description= todo.getDescription();
                cost = todo.getCost();

                //get and display name
                out.println("<h2>");
                out.println(todo.getItemName());
                out.println("</h2>");
                //end display name
            }
        }

//        String sql = "  \""+ generalName +"\" && Product.Location =  \""+productLocation+"\"";
        out.println("<div class=\"productContainer\"><img id=\"productContainerImg\" src=\"");
        out.println(productLocation + "\">");
        out.println("</div>");

        out.println("<ul class=\"itemCategories\">");
        out.println("<li class=\"productList\">");
        out.println("<div class=\"productListChoice\">");
        out.println("<h3>Change View:</h3>");
        out.println("</div>");
        out.println("<div class=\"list\">");
        out.println("<ul class=\"boxList\">");

        //display item information
        for(ItemList todo : todoList) {
            out.println("<li class='itemList'>");
            out.println("<a href=\"Item?product='" + todo.getGeneralName() + "'&amp;image='" + todo.getLocation() + "'\">");
            out.println("<img class=\"itemImg\" src= \"" + todo.getLocation() + "\" alt= \"" + todo.getGeneralName() + "\">");
            out.println("</a></li>");
        }
        //end display item information
        out.println("</ul>");
        out.println("</div>");
        out.println("</li>");
        out.println("<li class=\"sizeList\">");
        out.println("<div class=\"sizeChoice\">");
        out.println("<h3>Choose a Size:</h3>");
        out.println("</div>");
        out.println("<ul class=\"boxList\">");
        out.println("<li>");
        out.println("<a title=\"XXSmall\" class=\"sizeSelection\" id=\"xxs\" onclick=\"sizeSelect(this.id)\" >XXSmall</a>");
        out.println("</li>");
        out.println("<li>");
        out.println("<a title=\"XSmall\" class=\"sizeSelection\" id=\"xs\" onclick=\"sizeSelect(this.id)\">XSmall</a>");
        out.println("</li>");
        out.println("<li>");
        out.println("<a title=\"Small\" class=\"sizeSelection\" id=\"s\" onclick=\"sizeSelect(this.id)\">Small</a>");
        out.println("</li>");
        out.println("<li>");
        out.println("<a title=\"Medium\" class=\"sizeSelection\" id=\"m\" onclick=\"sizeSelect(this.id)\">Medium</a>");
        out.println("</li>");
        out.println("<li>");
        out.println("<a title=\"Large\" class=\"sizeSelection\" id=\"l\" onclick=\"sizeSelect(this.id)\">Large</a>");
        out.println("</li>");
        out.println("<li>");
        out.println("<a title=\"XLarge\" class=\"sizeSelection\" id=\"xl\" onclick=\"sizeSelect(this.id)\">XLarge</a>");
        out.println("</li>");
        out.println("<li>");
        out.println("<a title=\"XXLarge\" class=\"sizeSelection\" id=\"xxl\" onclick=\"sizeSelect(this.id)\">XXLarge</a>");
        out.println("</li>");
        out.println("</ul>");
        out.println("</li>");
        out.println("</ul>");
        out.println("<div id=\"btnContainer\">");

        //get item price
        out.println("<p id='cost'> $" + cost + "</p>");

        out.print("<form action='ShoppingCart' method='post'>");
        out.println("Quantity: <input id='quantity' type='text' name=\"quantity\" size='3' value=1> <br><br>"); //onkeyup="updateTotal()"
        out.println("<input id = 'item' type='text' name='itemName' value=\""+itemName+"\" hidden>");

        //submit button
        out.println("<input id=\"btn\" type=\"button\" value=\"Check out\" name=\"addButton\"onclick=\"addToCart('"+productLocation+"', '"+itemName+"')\"/>");
        out.println("</form>");


        String quantity = request.getParameter("quantity");
        String item = request.getParameter("itemName");
        String button = request.getParameter("addButton");
        HttpSession s;
        if(button != null) {
            s = request.getSession(true);
            s.setAttribute(item, quantity);
            out.println("<p> ADD PRESSED </p>");
        }

        out.println("</div>");
        out.println("</div>");
        out.println("<div class=\"descriptionContainer\">");
        out.println("<h3 id=\"descriptionHeader\"> Description</h3>");

        //get description for the server and put it here
        out.println("<p>" +description+ "</p>");
        out.println("</div>");

        //footer
        Constants.footer(out);

        //create a list
        List<ItemModel> viewedList = new ArrayList<ItemModel>();

        //access session
        HttpSession session = request.getSession();
        viewedList = (ArrayList)session.getAttribute("viewedList");

        if(viewedList == null) {
            viewedList = new ArrayList<ItemModel>();
        } else {

        }
        // if session doesn't have viewedList, add viewedList
        if (generalName != "" &&  productLocation != "" && productLocation != null)
        {
            boolean isInList = false;

            //check for duplicates
            for(int i = 0; i < viewedList.size(); ++i) {
                ItemModel tempItem = viewedList.get(i);
                if(tempItem.getImageLocation().equalsIgnoreCase(productLocation))
                    isInList = true;
            }

            //store when there is no duplicates
            if(!isInList) {
                //create an item object
                ItemModel itemViewed = new ItemModel();
                itemViewed.setGeneralName(generalName);
                itemViewed.setImageLocation(productLocation);

                viewedList.add(itemViewed);
            }
        }
       //add to Session list AND if size > 5, remove first itemViewed
        if (viewedList.size() > 5) {
            viewedList.remove(0);
        }
        session.setAttribute("viewedList", viewedList);
    }
}


