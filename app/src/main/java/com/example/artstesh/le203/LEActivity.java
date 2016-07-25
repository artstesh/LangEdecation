package com.example.artstesh.le203;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import static com.example.artstesh.le203.R.layout.drawer_list_item;


public class LEActivity extends ActionBarActivity implements View.OnClickListener, View.OnLongClickListener
{

	SharedPreferences preferences;

	public static List<String> arrayText = null;
	public static boolean rightMenuDetector = false;
	public int textSize;
	public static int filler;
	public int posOfRMenu = -1;
	public static Parter textParter;
	public static WordsCounter wordsCounter = new WordsCounter(4);
	public static HashMap<String, String> dictionary = new HashMap<String, String>();
	public static String translationLang;

	ButtonManager buttonManager;



	TextView FirstText;

	ListView llRightMenu;
	String[] listRightMenu;
	DrawerLayout mDrawer;
	ListView llLeftMenu;
	String[] listLeftMenu;


	public static DBHelper dbHelper;
	public static SQLiteDatabase database;
	public static ContentValues cv;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(com.example.artstesh.le203.R.layout.activity_le);

		dbHelper = new DBHelper(this, "translatorDB", null, 1);
		database = dbHelper.getWritableDatabase();
		cv = new ContentValues();

		try
		{
			InputStream is = getAssets().open("Dic.txt");
			Cursor cursor = database.query(DBHelper.DATABASE_NAME, null, null, null, null, null, null);

			//File inputeam = new File ("/data/data/com.example.artstesh.le203/databases/translatorDB");
			if(!(cursor.getCount() > 10))
			{
				Dictionary.fullDictionary(is);
			}
			is.close();
		} catch(IOException ignored)
		{
		}


		if(android.os.Build.VERSION.SDK_INT > 9)
		{
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		buttonManager = ButtonManager.getButtonManager();

		buttonManager.setBut_ans1((Button) findViewById(com.example.artstesh.le203.R.id.but_ans1));
		buttonManager.setBut_ans2((Button) findViewById(com.example.artstesh.le203.R.id.but_ans2));
		buttonManager.setBut_ans3((Button) findViewById(com.example.artstesh.le203.R.id.but_ans3));
		buttonManager.setBut_ans4((Button) findViewById(com.example.artstesh.le203.R.id.but_ans4));
		buttonManager.setBut_ans5((Button) findViewById(com.example.artstesh.le203.R.id.but_ans5));
		buttonManager.setBut_ans6((Button) findViewById(com.example.artstesh.le203.R.id.but_ans6));
		FirstText = (TextView) findViewById(com.example.artstesh.le203.R.id.FirstText);
		buttonManager.setBtnStart((Button) findViewById(com.example.artstesh.le203.R.id.buttonWork));
		buttonManager.setBtnNextStep((Button) findViewById(com.example.artstesh.le203.R.id.startButton));
		buttonManager.setOnClickListen(this);
		buttonManager.setOnLongClickListen(this);
		buttonManager.answerButtonsEnable(false);
		buttonManager.getBtnStart().setEnabled(false);
		buttonManager.getBtnNextStep().setEnabled(false);

		loadPreferences();
		// Инициализируем слайдменю

		llRightMenu = (ListView) findViewById(com.example.artstesh.le203.R.id.lvRightMenu);
		llLeftMenu = (ListView) findViewById(com.example.artstesh.le203.R.id.lvLeftMenu);
		listRightMenu = new String[]{};
		listLeftMenu = new String[]{"maintext.txt"};
		llRightMenu.setAdapter(new ArrayAdapter<String>(this, drawer_list_item, listRightMenu));
		llRightMenu.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{

				resetVars();
				arrayText = textParter.getParagraph(position);
				wordsCounter.setMaxPieceLength(arrayText.size());
				posOfRMenu = position;
				buttonManager.getBtnNextStep().setEnabled(true);
				buttonManager.getBtnStart().setEnabled(false);
				buttonManager.getBtnStart().setTextColor(Color.BLACK);
				FirstText.setTextColor(Color.BLACK);
				FirstText.setEnabled(false);
				buttonManager.getBtnStart().setTextColor(Color.GREEN);
				buttonManager.getBtnStart().setEnabled(true);
				buttonManager.answerButtonsEnable(false);
				nextStep();
				buttonManager.getBtnNextStep().setEnabled(true);
			}
		});

		try
		{
			FileInputStream inputStream = openFileInput("maintext.txt");
			if(inputStream != null)
			{
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader bufReader = new BufferedReader(reader);
				String line;
				StringBuilder builder = new StringBuilder();
				while((line = bufReader.readLine()) != null)
				{
					builder.append(line).append("\n");
				}
				FirstText.setText(builder.toString());  //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				rightMenuDetector = false;

				textParter = new Parter(builder.toString());
				inputStream.close();
			}
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		listRightMenu = textParter.getList();
		llRightMenu.setAdapter(new ArrayAdapter<String>(this, drawer_list_item, listRightMenu));
		arrayText = textParter.getListOfWords();
		wordsCounter.setMaxPieceLength(arrayText.size());
		FirstText.setTextColor(Color.BLACK);
		buttonManager.getBtnStart().setTextColor(Color.GREEN);
		buttonManager.getBtnStart().setEnabled(true);
		buttonManager.answerButtonsEnable(false);
		nextStep();
		buttonManager.getBtnNextStep().setEnabled(true);

		//buttonWork.setEnabled(false);

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.example.artstesh.le203.R.menu.menu_le, menu);
		return true;
	}

	@Override
	public boolean onLongClick(View v)
	{
		buttonManager.translateButtonText(v);
		return true;
	}

	public interface SideMenuListener
	{
		void startFragment(Fragment fragment);
		boolean toggleMenu();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();

		if(id == com.example.artstesh.le203.R.id.action_settings)
		{

		}

		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v)
	{
		Button button = (Button) v;

		if(v == buttonManager.getBtnNextStep())
		{
			wordsCounter.setWordNumber(wordsCounter.getPieceLength());
			nextWord();
			buttons_Full();
		}
		else if(v == buttonManager.getBtnStart())
		{
			buttonManager.getBtnStart().setTextColor(Color.BLACK);
			buttonManager.answerButtonsEnable(true);
			buttonManager.getBtnStart().setEnabled(false);
			buttonManager.getBtnNextStep().setEnabled(false);

			if(filler == 0)
			{
				FirstText.setText(null);
			}
			else
			{
				autoFiller(1);
			}
			buttons_Full();
		}
		else
		{
			int ans = answer_Button((String) button.getText());
			if(ans == 0)
			{
				button.setTextColor(Color.RED);
			}
		}
	}

	public void autoFiller(int tempor) //Принимаем значение отправителя: 0 numberWords, 1 - butcounter;
	{
		int c = 6*wordsCounter.getLearnStep();
		int a = (tempor > 0) ? wordsCounter.getWordNumber() : wordsCounter.getPieceLength();

		filler = (a >= c + 4) ? (a - c) : 0;

		String learn;

		learn = textParter.getPartOfFullText((a - 1), posOfRMenu);

		FirstText.setText(learn);
	}

	public void nextStep()
	{
		if(wordsCounter.getWordNumber() == 0)
		{
			wordsCounter.setPieceLength(wordsCounter.getLearnStep());
		}
		autoFiller(0);
	}

	public void nextWord()
	{
		if(wordsCounter.pieceLearned() || (wordsCounter.getWordNumber() == arrayText.size()-1))
		{
			autoFiller(1);

			buttonManager.getBtnStart().setEnabled(true);
			buttonManager.getBtnNextStep().setEnabled(true);
			buttonManager.getBtnStart().setTextColor(Color.GREEN);
			buttonManager.answerButtonsEnable(false);

			wordsCounter.setPieceLength(wordsCounter.getPieceLength() + wordsCounter.getLearnStep());

			FirstText.setText("");

			nextStep();
			wordsCounter.setWordNumber(filler);
		}

	}

	public void buttons_Full()
	{
		String tmp1, tmp2, tmp3, tmp4, tmp5, tmp6;

		tmp6 = (wordsCounter.isLastWord())
				? arrayText.get(wordsCounter.getWordNumber()) : arrayText.get(arrayText.size() - 1);

		if(tmp6 != null && tmp6.equalsIgnoreCase("&***&"))
		{
			answer_Button("&***&");
			return;
		}

		tmp1 = textParter.getRandomWord();
		tmp2 = textParter.getRandomWord();
		tmp3 = textParter.getRandomWord();
		tmp4 = textParter.getRandomWord();
		tmp5 = textParter.getRandomWord();

		buttonManager.setButtonsText(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6);
	}

	public int answer_Button(String tmp)
	{
		tmp = tmp.replace('\n', '\u0020');
		if(tmp.contains(" "))
		{
			String[] a = tmp.split(" ");
			tmp = a[0];
		}
		if(tmp.equalsIgnoreCase(arrayText.get(wordsCounter.getWordNumber())))
		{
			String temp = textParter.getPartOfFullText(wordsCounter.getWordNumber(), posOfRMenu);

			FirstText.setText(temp);
			wordsCounter.wordForward();

			buttons_Full();
			buttonManager.setAnswerButtonsBlack();
			nextWord();
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public static void resetVars()
	{
		wordsCounter.setPieceLength(0);
		wordsCounter.setWordNumber(0);
		filler = 0;
	}

	public void loadPreferences()
	{
		preferences = getSharedPreferences("settings.txt", Context.MODE_PRIVATE);
		//stepLength = preferences.getInt("savedStepLength",4);
		LEActivity.wordsCounter.setLearnStep(preferences.getInt("savedStepLength", 4));
		translationLang = preferences.getString("translationLang", LEActivity.translationLang);
	}

	@Override
	public void onStart()
	{
		super.onStart();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		Action viewAction = Action.newAction(Action.TYPE_VIEW, // TODO: choose an action type.
				"LE Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://com.example.artstesh.le203/http/host/path"));
		AppIndex.AppIndexApi.start(client, viewAction);
	}

	@Override
	public void onStop()
	{
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		Action viewAction = Action.newAction(Action.TYPE_VIEW, // TODO: choose an action type.
				"LE Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app deep link URI is correct.
				Uri.parse("android-app://com.example.artstesh.le203/http/host/path"));
		AppIndex.AppIndexApi.end(client, viewAction);
		client.disconnect();
	}

	@Override
	public android.support.v4.app.FragmentManager getSupportFragmentManager()
	{
		return null;
	}
}
