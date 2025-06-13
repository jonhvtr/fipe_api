package com.jonhvtr.fipe_api.principal;

import com.jonhvtr.fipe_api.model.DataFipe;
import com.jonhvtr.fipe_api.model.DataTable;
import com.jonhvtr.fipe_api.service.ConsumingData;
import com.jonhvtr.fipe_api.service.ConvertingData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    Scanner scan = new Scanner(System.in);
    private final String ADDRESS = "https://fipe.parallelum.com.br/api/v2/";
    private final ConsumingData consumingData = new ConsumingData();
    private final ConvertingData convertingData = new ConvertingData();

    public void showMenu() {
        System.out.println("""
                **OPÇÕES:**
                CARS
                MOTORCYCLES
                TRUCKS
                
                Digite a opção desejada:
                """);
        String vehicleType = scan.nextLine();
        String query = URLEncoder.encode(vehicleType, StandardCharsets.UTF_8);
        String address = ADDRESS + query + "/brands";
        String json = consumingData.getData(address);

        List<DataTable> dataTables = convertingData.getDataList(json, DataTable.class);
        dataTables.forEach(System.out::println);

        System.out.println("Digite o nome da marca desejada:");
        String nameVehicleBrand = scan.nextLine();
        List<DataTable> brandFilter = dataTables.stream()
                .filter(m -> m.name().toLowerCase().contains(nameVehicleBrand.toLowerCase()))
                .toList();

        System.out.println("Marca filtrada:");
        brandFilter.forEach(System.out::println);

        System.out.println("Digite o código marca desejado:");
        String vehicleBrand = scan.nextLine();
        var addressBrand = ADDRESS + query + "/brands/" + vehicleBrand + "/models";
        String jsonBrand = consumingData.getData(addressBrand);
        List<DataTable> dataBrand = convertingData.getDataList(jsonBrand, DataTable.class);
        dataBrand.forEach(System.out::println);

        System.out.println("Digite o nome do modelo desejado:");
        String nameVehicleModel = scan.nextLine();
        List<DataTable> modelFilter = dataBrand.stream()
                .filter(m -> m.name().toLowerCase().contains(nameVehicleModel.toLowerCase()))
                .toList();

        System.out.println("Modelos filtrados:");
        modelFilter.forEach(System.out::println);

        System.out.println("Digite o código modelo desejado:");
        String vehicleModel = scan.nextLine();
        var addressModel = ADDRESS + query + "/brands/" + vehicleBrand + "/models/" +
                vehicleModel + "/years";
        String jsonModel = consumingData.getData(addressModel);
        List<DataTable> dataModel = convertingData.getDataList(jsonModel, DataTable.class);

        List<DataFipe> dataFipeList = new ArrayList<>();

        for (int i = 0; i < dataModel.size(); i++) {
            var addressYear = ADDRESS + query + "/brands/" + vehicleBrand + "/models/" +
                    vehicleModel + "/years/" + dataModel.get(i).code();
            String jsonYear = consumingData.getData(addressYear);
            DataFipe dataFipe = convertingData.getData(jsonYear, DataFipe.class);
            dataFipeList.add(dataFipe);
        }

        System.out.println("Todos os veículos filtrados com avaliações por ano:");
        dataFipeList.forEach(System.out::println);
    }
}
