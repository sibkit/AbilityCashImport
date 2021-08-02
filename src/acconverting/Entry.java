package acconverting;

import java.math.BigDecimal;
import java.util.Date;

public class Entry
{
    private Date date;
    private BigDecimal amount;
    private String agent;
    private String description;
    private String document;


    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDocument()
    {
        return document;
    }

    public void setDocument(String document)
    {
        this.document = document;
    }

    public String getAgent()
    {
        return agent;
    }

    public void setAgent(String agent)
    {
        this.agent = agent;
    }
}
