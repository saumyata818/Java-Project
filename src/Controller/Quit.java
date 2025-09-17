

package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import javax.swing.*;
import java.util.Scanner;

public class Quit implements Operation {
    @Override
    public void operation(Database var1, JFrame var2, User var3) {

    }

    public void operation(Database var1, Scanner var2, User var3) {
        throw new Error("Unresolved compilation problem: \n\tThe method operation(Database, Scanner, User) of type Quit must override or implement a supertype method\n");
    }
}
