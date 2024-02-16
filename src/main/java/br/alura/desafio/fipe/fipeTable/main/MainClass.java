package br.alura.desafio.fipe.fipeTable.main;

import br.alura.desafio.fipe.fipeTable.model.Data;
import br.alura.desafio.fipe.fipeTable.model.Model;
import br.alura.desafio.fipe.fipeTable.model.Vehicle;
import br.alura.desafio.fipe.fipeTable.service.ApiConsumer;
import br.alura.desafio.fipe.fipeTable.service.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class MainClass {
    private final Scanner scanner = new Scanner(System.in);

    private final DataConverter converter = new DataConverter();

    private final ApiConsumer apiConsumer = new ApiConsumer();
    private String brand;
    private String carName;

    public void showMenu() {
        System.out.println("**** Opções ****");
        List<String> vehicleOptions = Arrays.asList("Carro", "Moto", "Caminhão");

        vehicleOptions.forEach(System.out::println);
        System.out.println();

        System.out.print("Digite uma da opções para consultar valores: ");
        var vehicleTypeSelected = scanner.nextLine();
        var response = ApiConsumer.getData(apiConsumer.getAddress(vehicleTypeSelected));

        var brandList = converter.getListData(response, Data.class);
        brandList.stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.print("Informe o código da marca para consulta: ");
        var brandSelect = scanner.nextLine();

        response = ApiConsumer.getData(apiConsumer.getAddress(vehicleTypeSelected) + brandSelect + "/modelos");
        var modelsList = converter.getData(response, Model.class);

        modelsList.models().stream()
                .sorted(Comparator.comparing(Data::code))
                .forEach(System.out::println);

        System.out.print("Digite o trecho do nome do veículo para consulta: ");
        var partialName = scanner.nextLine();
        List<Data> selectedModelList = modelsList.models()
                .stream()
                .filter(m -> m.description().toLowerCase().contains(partialName.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("Modelos encontrados: ");
        selectedModelList.forEach(System.out::println);

        System.out.print("Informe o código do carro para consulta: ");
        var selectedCar = scanner.nextLine();
        Optional<Data> searchedCar = selectedModelList.stream()
                .filter(m -> m.code().equals(selectedCar))
                .findFirst();

        List<Vehicle> vehicleList = new ArrayList<>();

        if (searchedCar.isPresent()) {
            response = ApiConsumer.getData(apiConsumer.getAddress(vehicleTypeSelected) + brandSelect + "/modelos/" + searchedCar.get().code() + "/anos");
            var yearsList = converter.getListData(response, Data.class);

            for (Data year: yearsList) {
                response = ApiConsumer.getData(apiConsumer.getAddress(vehicleTypeSelected) + brandSelect + "/modelos/" + searchedCar.get().code() + "/anos/" + year.code());
                vehicleList.add(converter.getData(response, Vehicle.class));
            }

            vehicleList.forEach(System.out::println);
        } else {
            System.out.println("Carro não encontrado");
        }
    }
}
