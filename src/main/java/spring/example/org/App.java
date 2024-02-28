package spring.example.org;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.Commun;
import spring.config.MyConfig;
import spring.models.User;
public class App 
{
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Commun commun = context.getBean("commun", Commun.class);
        String allUser =  commun.getAllUser();
        User user = new User();
        System.out.println(commun.addUser(allUser, user) + commun.updateUser(allUser) + commun.deleteUser(allUser, 3L));
    }
}
