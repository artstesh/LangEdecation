package com.example.artstesh.le203;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ParterTest
{
	
	static String text = "Вы можете определить собственные    границы транзакции," +
			" которые будут включать больше одной инструкции. " + "\n" + "\n" + "\n" +
			" Это повышает производительность, поскольку одна большая " +
			"транзакция выполняется быстрее, чем множество маленьких.";

	private List<String> listOfWords = Arrays.asList("Вы","можете","определить",
				"собственные","границы","транзакции,","которые","будут","включать",
				"больше","одной","инструкции.","&***&","Это","повышает",
				"производительность,","поскольку","одна","большая","транзакция",
				"выполняется","быстрее,","чем","множество", "маленьких.", "&***&");

	private List<String> listParOne = Arrays.asList("Вы","можете","определить",
			"собственные","границы","транзакции,","которые","будут","включать",
			"больше","одной","инструкции.");

	private List<String> listParTwo = Arrays.asList("Это","повышает",
			"производительность,","поскольку","одна","большая","транзакция",
			"выполняется","быстрее,","чем","множество", "маленьких.");

	private List<List<String>> listOfParagraphs;
	private String[] list;
	private String fullText;

	Parter parter = new Parter(text);



	@Test
	public void testGetListOfWords()
	{
		assertTrue(listOfWords.equals(parter.getListOfWords()));
	}

	@Test
	public void testGetParagraph() throws Exception
	{
		assertTrue(listParOne.equals(parter.getParagraph(0)));
		assertTrue(listParTwo.equals(parter.getParagraph(1)));
	}

	@Test
	public void testSetText() throws Exception
	{

	}

	@Test
	public void testGetPartOfFullText() throws Exception
	{
		String part = "Вы можете определить собственные границы";
		assertTrue(part.equals(parter.getPartOfFullText(4, 0)));
		String part2 = "Это повышает производительность, поскольку";
		assertTrue(part2.equals(parter.getPartOfFullText(3, 1)));
	}

	@Test
	public void testGetText() throws Exception
	{
		String[] massive = {"Вы можете определить собственные границы транзакции, которые будут включать больше одной инструкции.",
				"Это повышает производительность, поскольку одна большая транзакция выполняется быстрее, чем множество маленьких."};
		assertTrue(Arrays.equals(massive, parter.getText()));
	}

	@Test
	public void testGetlist() throws Exception
	{

		String[] testList = new String[2];
		testList[0] = "Вы можете определить собственные границы ";
		testList[1] = "Это повышает производительность, поскольку одна ";

		assertTrue(Arrays.equals(testList, parter.getList()));
	}

	@Test
	public void testGetRandomWord() throws Exception
	{


	}

	@Test
	public void testAnalyzer() throws Exception
	{
		assert (1==1);
	}
}