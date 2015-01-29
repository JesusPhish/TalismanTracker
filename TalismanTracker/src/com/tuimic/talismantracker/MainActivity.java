package com.tuimic.talismantracker;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnItemSelectedListener {
	
	/**
	 * These should go into their own class
	 */
	String name;
	int baseStr = 4;
	int baseCft = 4;
	int baseLife = 4;
	int baseFate = 4;
	
	int currStr = baseStr;
	int currCft = baseCft;
	int currLife = baseLife;
	int currFate = baseFate;
	
	int gold = 4;
	String startingZone = "test";
	
	String alignment = "test";
	/**
	 * End of that class
	 */
	
	Button btnAddStr, btnSubStr, btnAddCft, btnSubCft, btnAddLife, btnSubLife, btnAddFate, btnSubFate;
	
	TextView tvStr, tvCft, tvLife, tvFate;

	Spinner classMenu;
	String [] classListNames;
	Adventurer[] classListCharacters = new Adventurer[14];
	
	String class_selected;
	int lastSelected = 0;
	
	public void updateInfo(){
		boolean found = false;
		int i = 0;
		
		/**
		 * parse through the classes until you find the one that was selected
		 */	
		while(!found){
			if(class_selected.equals(classListCharacters[i].name)){
				found = true;
				//set the variables of this instance to that class
				tvStr.setText("Str: " + classListCharacters[i].baseStr);
				tvCft.setText("Cft: " + classListCharacters[i].baseCft);
				tvLife.setText("Life: " + classListCharacters[i].baseLife);
				tvFate.setText("Fate: " + classListCharacters[i].baseFate);
				
				currStr = classListCharacters[i].baseStr;
				currCft = classListCharacters[i].baseCft;
				currLife = classListCharacters[i].baseLife;
				currFate = classListCharacters[i].baseFate;
			}
			i++;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create a list of the classes
		classListNames = getResources().getStringArray(R.array.class_list);
		
		//generateAdventurerData(classListNames);
		classListCharacters[0] = new Adventurer(classListNames[0], 3, 3, 4, 3, 1, "City", "Evil"); //assassin
		classListCharacters[1] = new Adventurer(classListNames[1], 2, 4, 4, 4, 1, "Forest", "Neutral"); //druid
		classListCharacters[2] = new Adventurer(classListNames[2], 3, 3, 5, 5, 1, "Crags", "Neutral"); //dwarf
		classListCharacters[3] = new Adventurer(classListNames[3], 3, 4, 4, 3, 1, "Forest", "Good"); //elf
		classListCharacters[4] = new Adventurer(classListNames[4], 2, 4, 4, 4, 1, "Graveyard", "Evil"); //ghould
		classListCharacters[5] = new Adventurer(classListNames[5], 2, 4, 4, 5, 1, "Tavern", "Good"); //minstrel
		classListCharacters[6] = new Adventurer(classListNames[6], 2, 3, 4, 5, 1, "Village", "Good"); //monk
		classListCharacters[7] = new Adventurer(classListNames[7], 2, 4, 4, 5, 1, "Chapel", "Good"); //priest
		classListCharacters[8] = new Adventurer(classListNames[8], 2, 4, 4, 2, 1, "Chapel", "Good"); //prophetess
		classListCharacters[9] = new Adventurer(classListNames[9], 2, 4, 4, 3, 1, "Graveyard", "Evil"); //sorceress
		classListCharacters[10] = new Adventurer(classListNames[10], 3, 3, 4, 2, 1, "City", "Neutral"); //thief
		classListCharacters[11] = new Adventurer(classListNames[11], 6, 1, 6, 1, 1, "Crags", "Neutral"); //troll
		classListCharacters[12] = new Adventurer(classListNames[12], 4, 2, 5, 1, 1, "Tavern", "Neutral"); //warrior
		classListCharacters[13] = new Adventurer(classListNames[13], 2, 5, 4, 3, 1, "Graveyard", "Evil"); //wizard"
			
		classMenu = (Spinner) findViewById(R.id.spnClassMenu);
		// Create ArrayAdapter
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.class_list, android.R.layout.simple_spinner_item);
		// Specify the layout when the choices appear
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter settings to the spinner
		classMenu.setAdapter(adapter);
		classMenu.setOnItemSelectedListener(this);

		btnAddStr = (Button) findViewById(R.id.btnAddStr);
		btnSubStr = (Button) findViewById(R.id.btnSubStr);
		btnAddCft = (Button) findViewById(R.id.btnAddCft);
		btnSubCft = (Button) findViewById(R.id.btnSubCft);
		btnAddLife = (Button) findViewById(R.id.btnAddLife);
		btnSubLife = (Button) findViewById(R.id.btnSubLife);
		btnAddFate = (Button) findViewById(R.id.btnAddFate);
		btnSubFate = (Button) findViewById(R.id.btnSubFate);
		
		tvStr = (TextView) findViewById(R.id.tvStr);
		tvCft = (TextView) findViewById(R.id.tvCft);
		tvLife = (TextView) findViewById(R.id.tvLife);
		tvFate= (TextView) findViewById(R.id.tvFate);
		
		// set a listener
		btnAddStr.setOnClickListener(this);
		btnSubStr.setOnClickListener(this);
		btnAddCft.setOnClickListener(this);
		btnSubCft.setOnClickListener(this);
		btnAddLife.setOnClickListener(this);
		btnSubLife.setOnClickListener(this);
		btnAddFate.setOnClickListener(this);
		btnSubFate.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v){
		
		// perform the action required
		switch (v.getId()){
			case R.id.btnAddStr:
				this.currStr++;
				tvStr.setText("Str: " + currStr);
				break;
			case R.id.btnSubStr:
				if(currStr > 1 ){ //value cannot be less than 1
					currStr--;
					tvStr.setText("Str: " + currStr);
				}
				break;
			case R.id.btnAddCft:
				currCft++;
				tvCft.setText("Cft: " + currCft);
				break;
			case R.id.btnSubCft:
				if(currCft > 1){	//value cannot be less than 1
					currCft--;
					tvCft.setText("Cft: " + currCft);
				}
				break;
			case R.id.btnAddLife:
				currLife++;
				tvLife.setText("Life: " + currLife);
				break;
			case R.id.btnSubLife:
				if(currLife > 0){
					currLife--;
					tvLife.setText("Life:" + currLife);
				}
				break;
			case R.id.btnAddFate:
				currFate++;
				tvFate.setText("Fate: " + currFate);
				break;
			case R.id.btnSubFate:
				if(currFate > 0){
					currFate--;
					tvFate.setText("Fate: " + currFate);
				}
				break;
			default:
				break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, final int position,
			long id) {
		// TODO Auto-generated method stub
		
		if(lastSelected == position){
			System.out.println("the objects are the same");
			return;
		}

		class_selected = parent.getItemAtPosition(position).toString();
		
		Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
		alertDialog.setTitle("Warning!");
		alertDialog.setMessage("Selecting a new character will reset all stats. Is this ok?");
		
		alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				lastSelected = position;
				updateInfo();
			}
		});
		alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				classMenu.setSelection(lastSelected);
			}
		});
		alertDialog.show();
				
		//Toast.makeText(parent.getContext(), "You selected : \n" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
		
		class_selected = parent.getItemAtPosition(position).toString();
		//add function to update the basic stats based on the character selected

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}

class Adventurer {
	String name;
	int baseStr = 0;
	int baseCft = 0;
	int baseLife = 0;
	int baseFate = 0;
	
	int gold = 1;
	String startingZone = "";	
	String alignment = "";
	
	Adventurer(String nameIn, int str, int cft, int life, int fate, int goldIn, String startingZoneIn, String alignmentIn){
		name = nameIn;
		baseStr = str;
		baseCft = cft;
		baseLife = life;
		baseFate = fate;
		gold = goldIn;
		startingZone = startingZoneIn;
		alignment = alignmentIn;
	}
}
