package org.shop;

import org.shop.api.ItemService;
import org.shop.api.OrderService;
import org.shop.api.ProductService;
import org.shop.api.ProposalService;
import org.shop.api.UserService;
import org.shop.data.Item;
import org.shop.data.Order;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;




/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
    	
    	//by name and type
        ProductService productService = ctx.getBean("productServiceImpl", ProductService.class);
        
        //by name
        OrderService orderService = (OrderService) ctx.getBean("orderServiceImpl");
        
        //by type
        ItemService itemService = ctx.getBean(ItemService.class);
        
        //by alias
        UserService userService = (UserService) ctx.getBean("userService");
        
        ProposalService proposalService = ctx.getBean(ProposalService.class);
        
        Product kindle = productService.getProductsByName("Kindle Touch").get(0);
        Proposal proposal = proposalService.getProposalsByProduct(kindle).get(0);
        
        orderService.createOrder(userService.getUserById((long) 2), proposal);
        
        for (Order order : orderService.getOrdersByUserId((long) 2)) {
        	System.out.println(order.getUser());
            System.out.println("Order with id: "+ order.getId() + " on date:" + order.getCreatedDate());
            
            for (Item item : itemService.getItemsByOrderId(order.getId())) {
            	System.out.println(item.getProduct());
                System.out.println("Price per order:"+ item.getPrice() );
               
            }
        }
    }
    
}
