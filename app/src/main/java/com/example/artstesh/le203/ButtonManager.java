package com.example.artstesh.le203;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by artstesh on 24.06.2016.
 */
public class ButtonManager
		//Button management, rename buttons, get them enabled or not and so on.
{
	private static ButtonManager manager;
	private Button but_ans1;
	private Button but_ans2;
	private Button but_ans3;
	private Button but_ans4;
	private Button but_ans5;
	private Button but_ans6;
	private Button btnStart;
	private Button btnNextStep;


	private List<Button> listButtons;

	public ButtonManager ()
	{
		listButtons = new ArrayList<Button> ();
	}

	public static ButtonManager getButtonManager ()
	{
		if (manager == null)
		{
			manager = new ButtonManager ();
		}

		return manager;
	}

	public Button getBtnNextStep ()
	{
		return btnNextStep;
	}

	public void setBtnNextStep (Button btnNextStep)
	{
		this.btnNextStep = btnNextStep;
		listButtons.add (this.btnNextStep);
	}

	public Button getBtnStart ()
	{
		return btnStart;
	}

	public void setBtnStart (Button btnStart)
	{
		this.btnStart = btnStart;
		listButtons.add (this.btnStart);
	}

	public Button getBut_ans6 ()
	{
		return but_ans6;
	}

	public void setBut_ans6 (Button but_ans6)
	{
		this.but_ans6 = but_ans6;
		listButtons.add (this.but_ans6);
	}

	public Button getBut_ans5 ()
	{
		return but_ans5;
	}

	public void setBut_ans5 (Button but_ans5)
	{
		this.but_ans5 = but_ans5;
		listButtons.add (this.but_ans5);
	}

	public Button getBut_ans4 ()
	{
		return but_ans4;
	}

	public void setBut_ans4 (Button but_ans4)
	{
		this.but_ans4 = but_ans4;
		listButtons.add (this.but_ans4);
	}

	public Button getBut_ans3 ()
	{
		return but_ans3;
	}

	public void setBut_ans3 (Button but_ans3)
	{
		this.but_ans3 = but_ans3;
		listButtons.add (this.but_ans3);
	}

	public Button getBut_ans2 ()
	{
		return but_ans2;
	}

	public void setBut_ans2 (Button but_ans2)
	{
		this.but_ans2 = but_ans2;
		listButtons.add (this.but_ans2);
	}

	public Button getBut_ans1 ()
	{
		return but_ans1;
	}

	public void setBut_ans1 (Button but_ans1)
	{
		this.but_ans1 = but_ans1;
		listButtons.add (this.but_ans1);
	}

	public List<Button> getListButtons ()
	{
		return listButtons;
	}

	public void answerButtonsEnable (boolean a)
	{
		for (int i = 0; i < 6; i++)
		{
			listButtons.get (i).setEnabled(a);
		}
	}

	public void setOnClickListen (View.OnClickListener listener)
	{
		for (Button button : listButtons)
		{
			button.setOnClickListener (listener);
		}
	}

	public void setOnLongClickListen (View.OnLongClickListener listener)
	{
		for (int i = 0; i < 6; i++)
		{
			listButtons.get (i).setOnLongClickListener (listener);
		}
	}

	public void setAnswerButtonsBlack ()
	{
		for (Button button : listButtons)
		{
			button.setTextColor (Color.BLACK);
		}
	}

	public void translateButtonText (View btn)
	{
		Button button = (Button) btn;

		String tempKey = button.getText ().toString ();

		String msd = (!tempKey.contains ("\n")) ? transFromDB.translateWord (tempKey) : "";

		if (msd.equalsIgnoreCase ("-----"))
		{
			System.out.println ("We go to the NET!!!");
			try
			{
				msd = translFromNet.translate (tempKey);
			} catch (UnsupportedEncodingException ignored)
			{
			}
		}

		if (msd.length () > 0)
		{
			button.setText (tempKey + '\n' + msd);
		}
	}

	public void setButtonsText (String tmp1, String tmp2, String tmp3, String tmp4, String tmp5, String tmp6)
	{
		/*String[] texts = {tmp1, tmp2, tmp3, tmp4, tmp5, tmp6};*/

		List<String> words = Arrays.asList(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6);

		/*for (int i = 12; i > 0; i--)
		{
			int index = (int) (6 * Math.random ());
			int index2 = (int) (6 * Math.random ());
			String shuffle1 = texts[index];
			String shuffle2 = texts[index2];
			texts[index] = shuffle2;
			texts[index2] = shuffle1;
		}*/

		Collections.shuffle(words);

		for (int i = 0; i < 6; i++)
		{
			listButtons.get(i).setText(words.get(i));
		}
	}
}
