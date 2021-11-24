package models.iterator;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<ICollege> iColleges = new ArrayList<>();
        ComputerCollege computerCollege = new ComputerCollege(3);
        InfoCollege infoCollege = new InfoCollege();
        iColleges.add(computerCollege);
        iColleges.add(infoCollege);

        OutputImpl output = new OutputImpl(iColleges);
        output.printCollege();
    }
}
