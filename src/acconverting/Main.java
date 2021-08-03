package acconverting;

import acconverting.csvreaders.AlfaBankCsvReader;
import acconverting.csvreaders.RosbankCsvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main
{
    static void testList()
    {
        ArrayList<Integer> list = new ArrayList<>();

        long l1 = System.nanoTime();

        long mallocTime=0;

        for(int i=0;i<100_000_000;i++)
        {
            //long mt1 = System.nanoTime();
            Integer d = i;
            //long mt2 = System.nanoTime();
            //mallocTime+=(mt2-mt1);
            list.add(d);

        }

        long l2 = System.nanoTime();
        System.out.println("all time:"+(l2 - l1));
        System.out.println("malloc time: "+mallocTime);
    }

    static void convert20210514(String datePrefix) throws Exception
    {
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_"+datePrefix+".xml");
        //convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_"+datePrefix+".xml");
        //convert("Альфа (ИП Ф)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Фролов (Альфа)\\ИП Фролов Альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\ipf_alfa_"+datePrefix+".xml");
    }

    static void convert20210402(String datePrefix) throws Exception
    {
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_"+datePrefix+".xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_"+datePrefix+".xml");
        //convert("Альфа (ИП Ф)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Фролов (Альфа)\\ИП Фролов Альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\ipf_alfa_"+datePrefix+".xml");
    }


    static void convert2021(String datePrefix) throws Exception
    {
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_"+datePrefix+".xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_"+datePrefix+".xml");
        convert("Альфа (ИП Ф)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Фролов (Альфа)\\ИП Фролов Альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\ipf_alfa_"+datePrefix+".xml");
    }

    static void convert2020(String datePrefix) throws Exception
    {
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_"+datePrefix+".xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа "+datePrefix+".csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_"+datePrefix+".xml");
    }

     public static void main(String[] args) throws Exception
    {
        convertRB("Росбанк (ИП Ф)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Фролов (Росбанк)\\ИП Фролов Росбанк 2021.07.29.CSV","D:\\Ledmaster\\Финансы\\AC_import\\ipf_rb_2021.07.29.xml");
        convert20210514("2021.07.29");


        //convertRB("Росбанк (ИП Ф)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Фролов (Росбанк)\\ИП Фролов Росбанк 2021.06.29.CSV","D:\\Ledmaster\\Финансы\\AC_import\\ipf_rb_2021.06.29.xml");
        //convert20210514("2021.06.28");

        //convert20210402("2021.04.02");
        //convert2021("2021.03.04");

        //convert2020("2021.01.28");
        //convert("2020.12.24");
        //convert("2020.11.30");
        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.11.05.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.11.05.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.11.05.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.11.05.xml");
        */
        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.08.27.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.08.27.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.08.27.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.08.27.xml");
        */
        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.07.29.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.07.29.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.07.29.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.07.29.xml");
*/

        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.06.27.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.06.27.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.06.27.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.06.27.xml");
        */

        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.05.26.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.05.26.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.05.26.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.05.26.xml");
        */
        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.04.25.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.04.25.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.04.25.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.04.25.xml");
        */

        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.04.06.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.04.06.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.04.06.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.04.06.xml");
        */

/*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 2020.03.02.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_2020.03.02.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 2020.03.02.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_2020.03.02.xml");
*/


        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 20200122.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_20200122.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 20200122.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_20200122.xml");
        convert("Альфа (ИП М)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Мальцева (Альфа)\\ИП Мальцева Альфа 20200122.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipm_alfa_20200122.xml");
        */

        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 20191220.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_20191220.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 20191220.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_20191220.xml");
        convert("Альфа (ИП М)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Мальцева (Альфа)\\ИП Мальцева Альфа 20191220.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipm_alfa_20191220.xml");
        */
        /*
        convert("Альфа (LM)","D:\\Ledmaster\\Финансы\\Выписки банка\\Ледмастер (Альфа)\\Ледмастер альфа 20191126.csv","D:\\Ledmaster\\Финансы\\AC_import\\lm_alfa_20191126.xml");
        convert("Альфа (ИП Г)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Гаврилов (Альфа)\\ИП Гаврилов Альфа 20191126.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipg_alfa_20191126.xml");
        convert("Альфа (ИП М)","D:\\Ledmaster\\Финансы\\Выписки банка\\ИП Мальцева (Альфа)\\ИП Мальцева Альфа 20191126.csv","D:\\Ledmaster\\Финансы\\AC_import\\ipm_alfa_20191126.xml");
        */



        /*
        CsvReader reader = new CsvReader("");
        //List<Record> records = reader.read("C:\\Users\\sibkit\\Desktop\\LEDMASTER\\Финансы\\Выписки банка\\Ледмастер альфа 20190911.csv");
        //List<Record> records = reader.read("C:\\Users\\sibkit\\Desktop\\LEDMASTER\\Финансы\\Выписки банка\\ИП Гаврилов альфа 20190911.csv");

        //List<Record> records = reader.read("C:\\Users\\sibkit\\Desktop\\LEDMASTER\\Учет\\Финансы\\Выписки банка\\Ледмастер альфа 20190924.csv");
        List<Record> records = reader.read("C:\\Users\\sibkit\\Desktop\\LEDMASTER\\Учет\\Финансы\\Выписки банка\\ИП Гаврилов альфа 20190924.csv");

        //abilitycashconverting.AcXmlWriter writer = new abilitycashconverting.AcXmlWriter("Альфа (Ледмастер)");
        //abilitycashconverting.AcXmlWriter writer = new abilitycashconverting.AcXmlWriter("Альфа (LM)");
        abilitycashconverting.AcXmlWriter writer = new abilitycashconverting.AcXmlWriter("Альфа (ИП Г)");


        writer.write(records,"C:\\Users\\sibkit\\Desktop\\LEDMASTER\\Учет\\Финансы\\AC_import\\alfa_ipg_20190924.xml");
        */
    }

    static void convertRB(String accountName, String sourceFileName, String targetFileName) throws Exception
    {
        RosbankCsvReader reader = new RosbankCsvReader(accountName);
        List<Entry> entries = reader.read(sourceFileName);
        AcXmlWriter writer = new AcXmlWriter(accountName);
        writer.write(entries,targetFileName);
    }

    static void convert(String accountName, String sourceFileName, String targetFileName) throws Exception
    {
        AlfaBankCsvReader reader = new AlfaBankCsvReader(accountName);
        List<Entry> entries = reader.read(sourceFileName);
        AcXmlWriter writer = new AcXmlWriter(accountName);
        writer.write(entries,targetFileName);
    }
}
