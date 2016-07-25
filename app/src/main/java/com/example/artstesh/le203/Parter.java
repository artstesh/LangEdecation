package com.example.artstesh.le203;

import java.util.ArrayList;
import java.util.List;


/**
 * Основной класс для работы с текстом, все функции по дроблению и очистке - здесь.
 */
public class Parter
{
	private String[] text;
	private String[] list;
	private String fullText;  //

	private List<String> listOfWords;
	private List<List<String>> listOfParagraphs;

	public Parter (String textNotDivided)
	{
		fullText = textNotDivided;
		textDivide ();
		createListWords ();
		defineText ();

		setList ();
	}

	private void defineText ()
	{
		//Создаем массив абзацев, ВРЕМЕННО, УСТРАНИТЬ ЭТУ ХЕРЬ, КАК МОЖНО БЫСТРЕЕ!!!!!!!!!!!!
		text = new String[listOfParagraphs.size ()];
		for (int i = 0; i < listOfParagraphs.size (); i++)
		{
			List<String> list = listOfParagraphs.get (i);
			text[i] = "";
			for (String word : list)
			{
				text[i] += word + " ";
			}
			text[i] = text[i].trim ();
		}
	}

	public List<String> getListOfWords ()
	{
		return listOfWords;
	}

	public List<String> getParagraph (int number)
	{
		return listOfParagraphs.get (number);
	}

	private void createListWords ()
	{
		// Создаем лист всех слов в тексте

		listOfWords = new ArrayList<String> ();
		String[] temp = fullText.split (" ");
		int paragraph = 0; //Заглушка, если на вход подается одна строчка, то разделение не срабатывает
		//забиваем на перенос по строкам без окончания предложения, если подана одна
		//строчка

		for (String word : temp)
		{
			if (!word.equals (" ") && (word.length () > 0))
			{
				if (word.equalsIgnoreCase ("&***&"))
				{
					paragraph++;
				}
				listOfWords.add (word.trim ());
			}
		}

		for (int i = 1; i < listOfWords.size (); i++)
		{
			if (listOfWords.get (i).equals ("&***&") && listOfWords.get (i - 1).equals ("&***&"))
			{
				listOfWords.remove (i - 1);
				i--;
			}
			//Если перенос строки осуществлен в середине предложения, убирает его
			else if (listOfWords.get (i).equals ("&***&") && !(listOfWords.get (i - 1).contains (".") || listOfWords.get (i - 1).contains ("!") || listOfWords.get (i - 1).contains ("?") || listOfWords.get (i - 1).contains ("...") || listOfWords.get (i - 1).contains (";") || listOfWords.get (i - 1).contains (",")))
			{
				listOfWords.remove (i);
				i--;
			}
		}

		if (paragraph < 2 && !listOfWords.get (listOfWords.size () - 1).equalsIgnoreCase ("&***&"))
		{
			listOfWords.add ("&***&");
		}

		createListParagraphs ();
	}

	private void createListParagraphs ()
	{
		//Создаем лист, отдельных абзацев
		listOfParagraphs = new ArrayList<List<String>> ();
		List<String> paragraph = new ArrayList<String> ();
		for (String word : listOfWords)
		{

			if (!word.equalsIgnoreCase ("&***&"))
			{
				paragraph.add (word);
			}
			else
			{
				listOfParagraphs.add (paragraph);
				paragraph = new ArrayList<String> ();
			}
		}
	}

	public void setText (String a)
	{

		fullText = a;
		textDivide ();
		createListWords ();
		defineText ();

		setList ();
	}

	public String getPartOfFullText (int point, int posOfRMenu)
	//Возвращает кусок текста от нулевой позиции, до слова номер point
	{

		String temp = "";
		List<String> paragraph = (posOfRMenu == -1) ? listOfWords : listOfParagraphs.get (posOfRMenu);
		for (int i = 0; i <= (point < paragraph.size () - 1 ? point : paragraph.size () - 1); i++)
		{
			if (!listOfWords.get (i).equalsIgnoreCase ("&***&"))
			{
				temp += paragraph.get (i) + " ";
			}
			else
			{
				temp = temp.trim () + "\n";
				System.out.print (temp);
			}
		}

		return temp.trim ();
	}

	private void setList ()
	{
		//Создаем массив для отображения содержания текста в правом меню
		list = new String[listOfParagraphs.size ()];

		for (int i = 0; i < listOfParagraphs.size (); i++)
		{
			List<String> listWords = listOfParagraphs.get (i);

			int size = listWords.size () > 4 ? 4: listWords.size ();
			list[i] = "";
			for (int c = 0; c <= size; c++)
			{
				list[i] += listWords.get (c) + " ";
			}
		}
	}

	public String[] getText ()
	{
		return text;
	}

	public String[] getList()
	{
		return list;
	}

	private void textDivide ()
	{
		String[] temp = fullText.split ("\n");
		fullText = "";
		for (String line : temp)
		{
			fullText += line + " &***& ";
		}
	}

	public String getRandomWord ()
	{
		String result = listOfWords.get ((int) (listOfWords.size () * Math.random ()));
		return (!result.equalsIgnoreCase ("&***&")) ? result : getRandomWord ();
	}

	public int analyzer ()
	  /*
	  Классификатор текстов. Возвращает 0 для маленьих текстов не требующих разбивки.
     1 - для песен (среднее количество слов в абзаце меньше 14);
     */
	{
		int answer = 0;
		if (listOfWords.size () < 70)
		{
			answer = 0;
		}
		else
		{
			int koef = listOfWords.size () / listOfParagraphs.size ();
			if (koef < 14)
			{
				answer = 1;
			}
			else if (listOfParagraphs.size () > 0)
			{
				answer = 2;
			}
		}
		return answer;
	}
}
