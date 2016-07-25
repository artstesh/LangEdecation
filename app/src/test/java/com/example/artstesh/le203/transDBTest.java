package com.example.artstesh.le203;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by artstesh on 25.07.2016.
 */
public class transDBTest
{

	@Test
	public void formatTest()
	{
		assertTrue(formatWord("series").equals("sery"));
		assertTrue(formatWord("ies").equals("-----"));
		assertTrue(formatWord("boring").equals("bore"));
		assertTrue(formatWord("ing").equals("-----"));
		assertTrue(formatWord("closed").equals("close"));
		assertTrue(formatWord("sed").equals("-----"));
		assertTrue(formatWord("Robert's").equals("Robert"));
		assertTrue(formatWord("m's").equals("-----"));
		assertTrue(formatWord("closest").equals("close"));
		assertTrue(formatWord("ost").equals("-----"));
		assertTrue(formatWord("finally").equals("final"));
		assertTrue(formatWord("holy").equals("-----"));
		assertTrue(formatWord("closer").equals("close"));
		assertTrue(formatWord("oer").equals("-----"));
		assertTrue(formatWord("boss").equals("bos"));
		assertTrue(formatWord("oss").equals("-----"));
		assertTrue(formatWord("more").equals("mor"));
		assertTrue(formatWord("ore").equals("-----"));
		assertTrue(formatWord("more").equals("mor"));
		assertTrue(formatWord("ore").equals("-----"));
		assertTrue(formatWord("srcott").equals("srcot"));
		assertTrue(formatWord("scott").equals("-----"));
	}


	public static String formatWord(String modifWord)
	{
		int len = modifWord.length();
		String end1 = len <= 3 ? "qwerty" : modifWord.substring(len - 1, len);
		String end2 = len <= 5 ? "qwerty" : modifWord.substring(len - 2, len);
		String end3 = len <= 5 ? "qwerty" : modifWord.substring(len - 3, len);


		if (end3.equalsIgnoreCase("ies"))
		{
			return (modifWord.substring(0, len - 3) + "y");
		}
		else if (end3.equalsIgnoreCase("ing"))
		{
			return (modifWord.substring(0, len - 3) + "e");
		}

		else if (end2.equalsIgnoreCase("ed") || end2.equalsIgnoreCase("er") ||
					end2.substring(0, 1).equalsIgnoreCase(end2.substring(1)))
		{
			return (modifWord.substring(0, len - 1));
		}

		else if (end2.equalsIgnoreCase("'s") || end2.equalsIgnoreCase("st") ||
															 end2.equalsIgnoreCase("ly"))
		{
			return (modifWord.substring(0, len - 2));
		}

		else if (end1.equalsIgnoreCase("s") || end1.equalsIgnoreCase("e") && len > 3)
		{
			return (modifWord.substring(0, len - 1));
		}
		else
		{
			return "-----";
		}

	}
}
