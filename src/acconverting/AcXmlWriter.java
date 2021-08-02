package acconverting;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class AcXmlWriter
{
    String accountName;

    public AcXmlWriter(String accountName)
    {
        this.accountName = accountName;
    }

    void saveToFile(Document document, String filePath) throws TransformerException
    {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        Result result = new StreamResult(new File(filePath));
        Source source = new DOMSource(document);
        transformer.transform(source, result);
    }

    public void write(List<Entry> entries, String filePath) throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("ImportTemplate.xml");
        Node root = document.getDocumentElement();

        Node accountsNode = ((Element) root).getElementsByTagName("accounts").item(0);
        Node accountNode = ((Element) accountsNode).getElementsByTagName("account").item(0);
        Node acNameNode = ((Element) accountNode).getElementsByTagName("name").item(0);
        acNameNode.setTextContent(accountName);

        Node transactionsNode = ((Element) root).getElementsByTagName("transactions").item(0);



        for(Entry r : entries)
        {
            Element eTransaction = document.createElement("transaction");
            transactionsNode.appendChild(eTransaction);

            Element eDate = document.createElement("date");
            eTransaction.appendChild(eDate);

            eDate.appendChild(document.createTextNode(sdf.format(r.getDate())));

            if(r.getAmount().compareTo(new BigDecimal(0))>=0)
            {
                Element eIncome = document.createElement("income");
                eTransaction.appendChild(eIncome);

                Element eExecuted = document.createElement("executed");
                eIncome.appendChild(eExecuted);

                Element eIncomeAccount = document.createElement("income-account");
                eIncome.appendChild(eIncomeAccount);

                Element eIAName = document.createElement("name");
                eIncomeAccount.appendChild(eIAName);
                eIAName.appendChild(document.createTextNode(accountName));


                Element eEACurrency = document.createElement("currency");
                eIncomeAccount.appendChild(eEACurrency);
                eEACurrency.appendChild(document.createTextNode("RUR"));

                Element eIncomeAmount = document.createElement("income-amount");
                eIncome.appendChild(eIncomeAmount);
                eIncomeAmount.appendChild(document.createTextNode(r.getAmount().toString()));
            }
            else
            {
                Element eExpense = document.createElement("expense");
                eTransaction.appendChild(eExpense);

                Element eExecuted = document.createElement("executed");
                eExpense.appendChild(eExecuted);

                Element eExpenseAccount = document.createElement("expense-account");
                eExpense.appendChild(eExpenseAccount);

                Element eEAName = document.createElement("name");
                eExpenseAccount.appendChild(eEAName);
                eEAName.appendChild(document.createTextNode(accountName));

                Element eEACurrency = document.createElement("currency");
                eExpenseAccount.appendChild(eEACurrency);
                eEACurrency.appendChild(document.createTextNode("RUR"));

                Element eExpenseAmount = document.createElement("expense-amount");
                eExpense.appendChild(eExpenseAmount);
                eExpenseAmount.appendChild(document.createTextNode(r.getAmount().toString()));
            }

            Element eComment = document.createElement("comment");
            eTransaction.appendChild(eComment);
            eComment.appendChild(document.createTextNode("["+r.getAgent()+"] "+r.getDescription()));

            Element eExtraComment1 = document.createElement("extra-comment");
            eTransaction.appendChild(eExtraComment1);
            eExtraComment1.setAttribute("n","1");
            eExtraComment1.appendChild(document.createTextNode(r.getDocument()));

            Element eExtraComment4 = document.createElement("extra-comment");
            eTransaction.appendChild(eExtraComment4);
            eExtraComment4.setAttribute("n","4");
            eExtraComment4.appendChild(document.createTextNode("Выписка "+filePath));

        }



        saveToFile(document,filePath);

        System.out.println("--");
    }
}
