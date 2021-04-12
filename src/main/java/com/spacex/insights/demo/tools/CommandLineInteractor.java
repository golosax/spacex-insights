package com.spacex.insights.demo.tools;

import com.spacex.insights.demo.api.entity.RocketData;
import com.spacex.insights.demo.rspacex.facade.RocketsFacade;
import dnl.utils.text.table.TextTable;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.List;


@Component
@AllArgsConstructor
public class CommandLineInteractor implements CommandLineRunner {

    private RocketsFacade rocketsFacade;

    private static final String CONSOLE_PARAMETER = "--print_to_console";

    @Override
    public void run(String... args) throws Exception {
        if (isRunForConsole(args)) {
            printRocketsTableToConsole(rocketsFacade.getRocketsData());
        }
    }

    private void printRocketsTableToConsole(List<RocketData> rocketData) {
        String[] columnNames = {"Name",
                "Height (m)",
                "Mass (kg)",
                "Successfully launched ",
                "Failed",
                "Image"};

        TableModel jTable = new DefaultTableModel(transformToDoubleArray(rocketData), columnNames);

        TextTable textTable = new TextTable(jTable, true);
        textTable.printTable();
    }

    private static boolean isRunForConsole(String[] args) {
        return Arrays.asList(args).contains(CONSOLE_PARAMETER);
    }

    private Object[][] transformToDoubleArray(List<RocketData> rocketData) {
        if (rocketData == null || rocketData.isEmpty()) {
            return new Object[][]{};
        }
        Object[][] array = new Object[rocketData.size()][rocketData.get(0).toArray().length];

        for (int i = 0; i < rocketData.size(); i++) {
            array[i] = rocketData.get(i).toArray();
        }

        return array;
    }

}
