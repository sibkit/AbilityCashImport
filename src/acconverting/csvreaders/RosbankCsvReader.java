package acconverting.csvreaders;

import acconverting.Entry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RosbankCsvReader implements EntriesReader
{
    String accountName;

    public RosbankCsvReader(String accountName)
    {
        this.accountName = accountName;
    }

    List<String> columnNames;


    String getAgent(String[] line)
    {
        String agent = line[columnNames.indexOf("Корреспондент")];
        agent = agent.replace("Общество с ограниченной ответственностью", "ООО");
        agent = agent.replace("ИНДИВИДУАЛЬНЫЙ ПРЕДПРИНИМАТЕЛЬ", "ИП");
        agent = agent.replace("ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ", "ООО");
        agent = agent.replace("ОБЩЕСТВА С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ", "ООО");
        agent = agent.replace("Акционерное общество", "АО");
        agent = agent.replace("АКЦИОНЕРНОЕ ОБЩЕСТВО", "АО");
        agent = agent.replace("ТОРГОВЫЙ ДОМ", "ТД");
        agent = agent.replace("Управление Федерального казначейства", "УФК");
        agent = agent.replace("НАУЧНО ПРОИЗВОДСТВЕННОЕ ОБЪЕДИНЕНИЕ", "НПО");
        agent = agent.replace("Закрытое акционерное общество","ЗАО");

        return agent;
    }

    public List<Entry> read(String filePath) throws Exception
    {
        List<Entry> result = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        File f = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(f)))
        {
            String lineString;

            String caption1 = br.readLine();
            String caption2 = br.readLine();

            String[] columnNamesArray = br.readLine().split(";");
            columnNames = Arrays.asList(columnNamesArray);


            while ((lineString = br.readLine()) != null)
            {
                try
                {
                    String[] line = lineString.split(";");

                    if(line[0].equals("Обороты"))
                    {
                        break;
                    }

                    String date = line[columnNames.indexOf("Дата исходного документа")];
                    String d_c = line[4];
                    String sum = line[columnNames.indexOf("Сумма")];

                    Entry r = new Entry();

                    r.setDate(formatter.parse(date));
                    r.setAgent(getAgent(line));
                    r.setDescription(line[columnNames.indexOf("Содержание проводки")]);
                    r.setDocument("Операция №" + line[columnNames.indexOf("№ документа")].trim());
                    if (d_c.equals("\"Д\""))
                    {
                        r.setAmount(new BigDecimal(sum.replace(" ", "")));
                    } else
                    {
                        r.setAmount(new BigDecimal(sum.replace(" ", "")).multiply(new BigDecimal(-1)));
                    }
                    result.add(r);
                }
                catch (Exception ex)
                {
                    System.err.println("Error import data");
                }
            }
        }
        catch (Exception ex)
        {
            System.err.println("Error import data");
        }
        return result;
    }
}
