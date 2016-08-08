package com.example.artstesh.le203;

import android.database.Cursor;

import java.util.regex.Pattern;

/**
 * translating from database
 */
public class transFromDB
{

	public static String translateWord(String word)
	{
		String selection = DBHelper.WORD_COLUMN + "=?";
		String[] selectionArgs = new String[]{cutWord(word)};
		Cursor cursor = LEActivity.database.query(DBHelper.DATABASE_NAME, null,
				selection, selectionArgs, null, null, null);

		cursor.moveToFirst();
		String msd;
		if (cursor.getCount() > 0)
		{
			msd = cursor.getString(cursor.getColumnIndex("translation"));
		}
		else
		{
			msd = formatWord(word);
		}
		cursor.close();
		System.out.println("Variant of DB is " + msd);
		return msd;
	}

	/*Отсекаем от слова знаки пунктуации, цифры*/
	private static String cutWord(String word)
	{
		char[] arChar = word.toCharArray();
		String tmp = "";
		for (int i = 0; i < arChar.length; i++)
		{
			if (Pattern.matches("[A-Za-z]", Character.toString(arChar[i])))
			{
				tmp += arChar[i];
			}
			else if((arChar[i] == '-') && (i != 0) && (i != arChar.length-1))
			{
				tmp += arChar[i];
			}
		}
		String temp = tmp.toLowerCase();
		return temp;
	}

	/*Отсекаем окончания, суффиксы и проч. и пытаемся перевести "голое" слово*/
	private static String formatWord(String modifWord)
	{
		int len = modifWord.length();
		String end1 = len <= 3 ? "qwerty" : modifWord.substring(len - 1, len);
		String end2 = len <= 5 ? "qwerty" : modifWord.substring(len - 2, len);
		String end3 = len <= 5 ? "qwerty" : modifWord.substring(len - 3, len);

		if (end3.equalsIgnoreCase("ies"))
		{
			modifWord =  (modifWord.substring(0, len - 3) + "y");
		}
		else if (end3.equalsIgnoreCase("ing"))
		{
			modifWord =  (modifWord.substring(0, len - 3) + "e");
		}

		else if (end2.equalsIgnoreCase("ed") || end2.equalsIgnoreCase("er") ||
					end2.substring(0, 1).equalsIgnoreCase(end2.substring(1)))
		{
			modifWord =  (modifWord.substring(0, len - 1));
		}

		else if (end2.equalsIgnoreCase("'s") || end2.equalsIgnoreCase("st") ||
					end2.equalsIgnoreCase("ly"))
		{
			modifWord =  (modifWord.substring(0, len - 2));
		}

		else if (end1.equalsIgnoreCase("s") || end1.equalsIgnoreCase("e") && len > 3)
		{
			modifWord =  (modifWord.substring(0, len - 1));
		}
		else
		{
			modifWord =  "-----";
			return modifWord;
		}
		return translateWord(modifWord);
	}


}
