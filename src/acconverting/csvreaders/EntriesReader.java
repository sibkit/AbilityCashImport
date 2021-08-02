package acconverting.csvreaders;

import acconverting.Entry;

import java.util.List;

public interface EntriesReader
{
    List<Entry> read(String filePath) throws Exception;
}
