package com.example.artstesh.le203;


public class WordsCounter
{
    private int wordNumber = 0;
    private int learnStep;
    private int pieceLength = 0;
    private int maxPieceLength;

    public boolean pieceLearned()
    {
        return (pieceLength <= wordNumber);
    }

    public boolean isLastWord()
    {
        return wordNumber < maxPieceLength;
    }

    public int getMaxPieceLength()
    {
        return maxPieceLength;
    }

    public void setMaxPieceLength(int maxPieceLength)
    {
        this.maxPieceLength = maxPieceLength;
    }

    WordsCounter(int learnStep)
    {
        this.learnStep = learnStep;
    }

    public void wordForward()
    {
        if(wordNumber < maxPieceLength - 2)wordNumber++;
    }

    public int getWordNumber()
    {
        return wordNumber < maxPieceLength -1 ? wordNumber : maxPieceLength-1;
    }

    public void setWordNumber(int wordNumber)
    {
        this.wordNumber = wordNumber;
    }

    public int getLearnStep()
    {
        return learnStep;
    }

    public void setLearnStep(int step)
    {
        learnStep = step;
    }

    public int getPieceLength()
    {
        return pieceLength;
    }

    public void setPieceLength(int pieceLength)
    {
        this.pieceLength = (pieceLength > maxPieceLength) ? maxPieceLength : pieceLength;
        System.out.println(this.pieceLength);
    }

    public void stepForward()
    {
        wordNumber += learnStep;
    }
}
