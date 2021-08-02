package acconverting.csvreaders;

import acconverting.Entry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AlfaBankCsvReader implements EntriesReader
{
    String accountName;

    public AlfaBankCsvReader(String accountName)
    {
        this.accountName = accountName;
    }


    List<String> captions = new ArrayList<>();
    List<String> columnNames = new ArrayList<>();


    String getAgent(String[] line)
    {
        String agentCaption;
        if(line[captions.indexOf("d_c")].equals("C"))
            agentCaption = "plat_name";
        else
            agentCaption = "pol_name";
        String agent = line[captions.indexOf(agentCaption)];
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

        try (BufferedReader br = new BufferedReader(new FileReader(f,Charset.forName("Cp1251"))))
        {
            String lineString;

            String[] captionsArray = br.readLine().split("\t");
            for(String s : captionsArray)
                captions.add(s);

            String[] columnNamesArray = br.readLine().split("\t");
            for(String s : columnNamesArray)
                columnNames.add(s);


            while ((lineString = br.readLine()) != null)
            {
                String[] line = lineString.split("\t");

                String date = line[captions.indexOf("date_oper")];
                String d_c = line[captions.indexOf("d_c")];
                String sum = line[captions.indexOf("sum_val")];

                Entry r = new Entry();

                r.setDate(formatter.parse(date));
                r.setAgent(getAgent(line));
                r.setDescription(line[captions.indexOf("text70")]);
                r.setDocument("Операция №"+line[captions.indexOf("number")]);
                if(d_c.equals("C"))
                {
                    r.setAmount(new BigDecimal(sum.replace(",", ".")));
                }
                else
                {
                    r.setAmount(new BigDecimal(sum.replace(",", ".")).multiply(new BigDecimal(-1)));
                }
                result.add(r);
            }
        }
        return result;
    }
}
