package com.example.mdthai.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TicTacToe tttGame;
    private int pOneCounter;
    private int pTwoCounter;
    private int tieCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tttGame = new TicTacToeGameState();


    }

    @Override
    protected void onStart(){
        super.onStart();

        TextView tv = (TextView) findViewById(R.id.desc);
        ImageButton button = (ImageButton) findViewById(R.id.box1);

    }

    public void clickImages(View view)
    {
        ImageButton clickedBtn = (ImageButton) view;
        Square block = resolveButton(clickedBtn);
        if (block != null) {
            Winner result = tttGame.makeAMove(block);
            //TO DO: CHANGE IMAGE SRC
            String image = "";
            if (tttGame.getTurn() == false) {
                //Player 1 Image
                //TO DO: FIND IMAGE
                //clickedBtn.setImageResource();
            }
            else
            {
                //Player 2 Image
                //TO DO: FIND IMAGE
                //clickedBtn.setImageResource();
            }
            //DISABLE button
            clickedBtn.setEnabled(false);

            if (result == Winner.P_ONE || result == Winner.P_TWO || result == Winner.TIE){

                if (result == Winner.P_ONE){
                    pOneCounter++;
                }
                else if (result == Winner.P_TWO)
                {
                    pTwoCounter++;
                }
                else{
                    tieCounter++;
                }

                disableBtn();

            }
            else
            {
                if(tttGame.getAIMode())
                {
                    Square aiMove = tttGame.getAINextPosition();

                    Winner aiWin = tttGame.makeAMove(aiMove);
                    ImageButton aiBtn = (ImageButton) findViewById(findIDbySquare(aiMove));

                    aiBtn.setEnabled(false);
                    //TO DO: FIND IMAGE
                    //aiBtn.setImageResource();

                    if (aiWin == Winner.P_TWO || aiWin == Winner.TIE){
                        if(aiWin == Winner.P_TWO)
                            pTwoCounter++;
                        else
                            tieCounter++;

                        disableBtn();
                    }

                }
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putIntArray("board", tttGame.getBoard());
        savedInstanceState.putBoolean("turn", tttGame.getTurn());
        savedInstanceState.putBoolean("mode", tttGame.getAIMode());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        tttGame = new TicTacToeGameState();
        tttGame.setBoard(savedInstanceState.getIntArray("board"));
        tttGame.setTurn(savedInstanceState.getBoolean("turn"));
        tttGame.setAIMode(savedInstanceState.getBoolean("mode"));

    }

    /**
     * Returns a square enum
     * @param button ImageButton
     * @return Square enum
     */
    private Square resolveButton (ImageButton button)
    {
        String name = getResources().getResourceEntryName(button.getId());

        switch (name)
        {
            case "box1":
                return Square.ONE;
            case "box2":
                return Square.TWO;
            case "box3":
                return Square.THREE;
            case "box4":
                return Square.FOUR;
            case "box5":
                return Square.FIVE;
            case "box6":
                return Square.SIX;
            case "box7":
                return Square.SEVEN;
            case "box8":
                return Square.EIGHT;
            case "box9":
                return Square.NINE;
            default:
                return null;

        }

    }

    private int findIDbySquare(Square square)
    {
        switch (square.getValue()){
            case 0:
                return R.id.box1;
            case 1:
                return R.id.box2;
            case 2:
                return R.id.box3;
            case 3:
                return R.id.box4;
            case 4:
                return R.id.box5;
            case 5:
                return R.id.box6;
            case 6:
                return R.id.box7;
            case 7:
                return R.id.box8;
            default:
                return R.id.box9;
        }
    }

    /**
     * Disable all ImageButton objects
     */
    private void disableBtn(){
        int [] ids = new int[]{
                R.id.box1, R.id.box2, R.id.box3, R.id.box4, R.id.box4, R.id.box5, R.id.box6,
                R.id.box7, R.id.box8, R.id.box9
        };

        for (int i = 0; i < ids.length; i++)
        {
            ImageButton btnToDisable = (ImageButton)findViewById(ids[i]);
            btnToDisable.setEnabled(false);
        }

    }

    public void resetGame(View view)
    {
        boolean mode = tttGame.getAIMode();
        tttGame = new TicTacToeGameState(mode);



    }

    public void zeroCounters(View view)
    {
        tieCounter = 0;
        pTwoCounter = 0;
        pOneCounter =0;

    }

    private void resetBoard(){

        int [] ids = new int[]{
                R.id.box1, R.id.box2, R.id.box3, R.id.box4, R.id.box4, R.id.box5, R.id.box6,
                R.id.box7, R.id.box8, R.id.box9
        };

        for (int i = 0; i < ids.length; i++)
        {
            ImageButton btnToDisable = (ImageButton)findViewById(ids[i]);
            btnToDisable.setEnabled(true);
            //Change image back to default Image
            //btnToDisable.setImageResource();
        }
    }






}
