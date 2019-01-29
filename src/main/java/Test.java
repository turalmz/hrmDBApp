
import com.company.Context;
import com.company.entity.Employee;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TURAL
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Context.instanceCountryDao().getAll());
        Context.instanceEmployeeDao().insertEmployee(new Employee(null, "A", "B", "alma", "alma", null, null, 0.0, 0.0, null, null));
    }
}
