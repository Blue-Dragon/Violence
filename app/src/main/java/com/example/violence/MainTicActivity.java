package com.example.violence;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class MainTicActivity extends Activity {

    // Represents the internal state of the game
    private TicTacToeGame mGame;

    // Buttons making up the board
    private Button mBoardButtons[];
//    private Button mNewGame;

    // Various text displayed
    private TextView mInfoTextView;
    private TextView mPlayerOneCount;
    private TextView mTieCount;
    private TextView mPlayerTwoCount;
    private TextView mPlayerOneText;
    private TextView mPlayerTwoText;

    // Counters for the wins and ties
    private int mPlayerOneCounter = 0;
    private int mTieCounter = 0;
    private int mPlayerTwoCounter = 0;

    // Bools needed to see if player one goes first
    // if the gameType is to be single or local multiplayer
    // if it is player one's turn
    // and if the game is over
    private boolean mPlayerOneFirst = true;
    private boolean mIsSinglePlayer = false;
    private boolean mIsPlayerOneTurn = true;
    private boolean mGameOver = false;

    MediaPlayer mediaPlayer;


    /*****************************************************************************************/

    /** Called when the activity is first created. */
    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main_tic);

        boolean mGameType = getIntent().getExtras().getBoolean("gameType");

         mediaPlayer = MediaPlayer.create(this, R.raw.gamemusic);

        // Initialize the buttons
        mBoardButtons = new Button[mGame.getBOARD_SIZE()];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
//        addListenerOnButton();

        // setup the textviews
        mInfoTextView = (TextView) findViewById(R.id.information);
        mPlayerOneCount = (TextView) findViewById(R.id.humanCount);
        mTieCount = (TextView) findViewById(R.id.tiesCount);
        mPlayerTwoCount = (TextView) findViewById(R.id.androidCount);
        mPlayerOneText = (TextView) findViewById(R.id.human);
        mPlayerTwoText = (TextView) findViewById(R.id.android);

        // set the initial counter display values
        mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));

        // create a new game object
        mGame = new TicTacToeGame();

        // start a new game
        startNewGame(mGameType);

    }


    /*****************************************************************************************/

//
//    public void addListenerOnButton(){
//
//        mNewGame = (Button) findViewById(R.id.NewGame);
//
//        mNewGame.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                startNewGame(mIsSinglePlayer);
//            }
//        });
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        switch(item.getItemId())
//        {
//            case R.id.newGame:
//                startNewGame(mIsSinglePlayer);
//                break;
//            case R.id.exitGame:
//                MainTicActivity.this.finish();
//                break;
//        }
//
//        return true;
//    }



    // start a new game
    // clears the board and resets all buttons / text
    // sets game over to be false
    private void startNewGame(boolean isSingle)
    {

        this.mIsSinglePlayer = isSingle;

        mGame.clearBoard();

        for (int i = 0; i < mBoardButtons.length; i++)
        {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
            mBoardButtons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.blank));
        }


        if (mIsSinglePlayer)
        {
            mPlayerOneText.setText(R.string.human);
            mPlayerTwoText.setText(R.string.android);

            if (mPlayerOneFirst)
            {
                mInfoTextView.setText(R.string.first_human);
                mPlayerOneFirst = false;
            }
            else
            {
                mInfoTextView.setText(R.string.turn_computer);
                int move = mGame.getComputerMove();
                setMove(mGame.PLAYER_TWO, move);
                mPlayerOneFirst = true;
            }
        }
        else
        {
            mPlayerOneText.setText(R.string.player_one);
            mPlayerTwoText.setText(R.string.player_two);

            if (mPlayerOneFirst)
            {
                mInfoTextView.setText(R.string.turn_player_one);
                mPlayerOneFirst = false;
            }
            else
            {
                mInfoTextView.setText(R.string.turn_player_two);
                mPlayerOneFirst = true;
            }
        }

        mGameOver = false;
    }

    private class ButtonClickListener implements OnClickListener
    {
        int location;

        public ButtonClickListener(int location)
        {
            this.location = location;
        }

        public void onClick(View view)
        {
            if (!mGameOver)
            {
                if (mBoardButtons[location].isEnabled())
                {
                    if (mIsSinglePlayer)
                    {
                        setMove(TicTacToeGame.PLAYER_ONE, location);

                        int winner = mGame.checkForWinner();

                        if (winner == 0)
                        {
                            mInfoTextView.setText("");
                            int move = mGame.getComputerMove();
                            setMove(mGame.PLAYER_TWO, move);
                            winner = mGame.checkForWinner();
                        }

                        if (winner == 0) {
                            mInfoTextView.setText("");
                        }
                        else if (winner == 1)
                        {
                            showAlertDialog(R.string.result_tie);
//                            mInfoTextView.setText(R.string.result_tie);
                            mTieCounter++;
                            mTieCount.setText(Integer.toString(mTieCounter));
                            mGameOver = true;
                        }
                        else if (winner == 2)
                        {
                            showAlertDialog(R.string.result_human_wins);
                            mInfoTextView.setText("");
                            mPlayerOneCounter++;
                            mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                            mGameOver = true;
                        }
                        else
                        {
                            showAlertDialog(R.string.result_android_wins);
                            mInfoTextView.setText("");
                            mPlayerTwoCounter++;
                            mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                            mGameOver = true;
                        }
                    }
                    else
                    {
                        if (mIsPlayerOneTurn)
                            setMove(mGame.PLAYER_ONE, location);
                        else
                            setMove(mGame.PLAYER_TWO, location);

                        int winner = mGame.checkForWinner();

                        if (winner == 0)
                        {
                            if (mIsPlayerOneTurn)
                            {
                                mInfoTextView.setText(R.string.turn_player_two);
                                mIsPlayerOneTurn = false;
                            }
                            else
                            {
                                mInfoTextView.setText(R.string.turn_player_one);
                                mIsPlayerOneTurn = true;
                            }
                        }
                        else if (winner == 1)
                        {
                            showAlertDialog(R.string.result_tie);
                            mInfoTextView.setText("");
                            mTieCounter++;
                            mTieCount.setText(Integer.toString(mTieCounter));
                            mGameOver = true;
                        }
                        else if (winner == 2)
                        {
                            showAlertDialog(R.string.result_player_one_wins);
                            mInfoTextView.setText("");
                            mPlayerOneCounter++;
                            mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                            mGameOver = true;
                            mIsPlayerOneTurn = false;
                        }
                        else
                        {
                            showAlertDialog(R.string.result_player_two_wins);
                            mInfoTextView.setText("");
                            mPlayerTwoCounter++;
                            mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                            mGameOver = true;
                            mIsPlayerOneTurn = true;
                        }
                    }
                }
            }
        }
    }

    // set move for the current player
    private void setMove(char player, int location)
    {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        if (player == TicTacToeGame.PLAYER_ONE)
            mBoardButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.x));
        else
            mBoardButtons[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.o));
    }

    // do sth whenever MainActivity is open
    // play music
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    // do sth whenever MainActivity is closed
    // stop music
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    // Dialog
    protected void showAlertDialog(int string){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setMessage(string)
                .setCancelable(false)
                .setPositiveButton("بازی مجدد", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startNewGame(mIsSinglePlayer);
                    }
                })
                .setNegativeButton("خروج از بازی", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

        builder.show();
    }
}