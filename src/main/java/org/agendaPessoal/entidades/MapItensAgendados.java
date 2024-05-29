package org.agendaPessoal.entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MapItensAgendados {
    private TreeMap<LocalDate, ListaItensAgendados> itensAgendados;

    public MapItensAgendados() {
        this.itensAgendados = new TreeMap<>();
    }

    public void addListaItemAgendado(LocalDate date, ListaItensAgendados lista) {
        if (itensAgendados.containsKey(date)) {
            ListaItensAgendados exitingList = itensAgendados.get(date);
            exitingList.addAll(lista.getItensAgendados());
        } else {
            itensAgendados.put(date, lista);
        }
    }

    public void addItemAgendado(LocalDate date, ItemAgendado item) {
        if (itensAgendados.containsKey(date)) {
            ListaItensAgendados exitingList = itensAgendados.get(date);
            exitingList.adicionarItemAgendado(item);
        } else {
            ListaItensAgendados lista = new ListaItensAgendados();
            lista.adicionarItemAgendado(item);
            itensAgendados.put(date, lista);
        }

    }

    public ListaItensAgendados get(LocalDate date) {
        return itensAgendados.get(date);
    }

    public int size() {
        return itensAgendados.size();
    }

    public Map<LocalDate, ListaItensAgendados> getAllItensOfMonth(int month) {
        Map<LocalDate, ListaItensAgendados> itensDoMes = new HashMap<>();
        for (Map.Entry<LocalDate, ListaItensAgendados> entry : itensAgendados.entrySet()) {
            if (entry.getKey().getMonthValue() == month) {
                itensDoMes.put(entry.getKey(), entry.getValue());
            }
        }
        return itensDoMes;
    }

    public Map<LocalDate, ListaItensAgendados> getItensAgendadosOrdered() {
        return new TreeMap<>(itensAgendados);
    }

    public void getDataFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Map.Entry<LocalDate, ListaItensAgendados> entry : itensAgendados.entrySet()) {
                String date = entry.getKey().format(DateTimeFormatter.ISO_LOCAL_DATE);
                ListaItensAgendados lista = entry.getValue();

                // Convert each item in the list to a string and join them with commas
                String items = String.join(",", lista.getDataFromObjects());

                writer.write(date + "," + items + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
