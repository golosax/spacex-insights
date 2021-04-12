package com.spacex.insights.demo.tools;

import com.spacex.insights.demo.rspacex.gateway.entity.Rocket;
import com.spacex.insights.demo.rspacex.service.RocketService;
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

    private RocketService rocketService;

    private static final String CONSOLE_PARAMETER = "--print_to_console";

    @Override
    public void run(String... args) throws Exception {
        if (isRunForConsole(args)) {
            printRocketsTableToConsole(rocketService.getRockets());
        }
    }

    private void printRocketsTableToConsole(List<Rocket> rockets) {
        String[] columnNames = {"Name",
                "Height (m)",
                "Mass (kg)",
                "Image"};

        TableModel jTable = new DefaultTableModel(transformToDoubleArray(rockets), columnNames);

        TextTable textTable = new TextTable(jTable, true);
        textTable.printTable();
    }

    private static boolean isRunForConsole(String[] args) {
        return Arrays.asList(args).contains(CONSOLE_PARAMETER);
    }

    private Object[][] transformToDoubleArray(List<Rocket> rockets) {
        if(rockets == null || rockets.size() == 0) {
            return new Object[][]{};
        }
        Object[][] array = new Object[rockets.size()][rockets.get(0).toArray().length];

        for (int i = 0; i < rockets.size(); i++) {
            array[i] = rockets.get(i).toArray();
        }

        return array;
    }

}
