using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Media;
using BeepUtil.BeepSpace;
using System.IO;

namespace ChordTest2
{
    class Program
    {
        static void Main(string[] args)
        {

            Note.Chord("ceg");
            Note.PlayNote("-c", 2000);
            Note.PlayNote("-e", 2000);
            Note.PlayNote("-g", 2000);

            Note.PlayNote("-g", 5000);
            Note.PlayNote("-b", 5000);
            Note.PlayNote("-d", 5000);

            Note.PlayNote("b");
            Note.PlayNote("b");
            Note.PlayNote("g");
            return;
             
             Console.WriteLine("Welcome to numberMOOsic!");
            for (int i = 0; true; i++)
            {
                string whatLet = Console.ReadLine();
                string[] NOTES = whatLet.Split(' ');
                for (int w = 0; w < NOTES.Length; w++)
                {
                    int NumStart = 0;
                    string Letter = NOTES[w];
                    for(int z = 0; z < Letter.Length; z++)
                    {
                        //-a
                        NumStart = z;
                        if (char.IsNumber(Letter, z) == true)
                        {
                            break;
                        }
                        NumStart = z + 1;
                    }
                    string length = "0";
                    string note = Letter.Substring(0, NumStart);
                    if (NumStart + 1 < Letter.Length)
                    {
                        length = Letter.Substring(NumStart);
                    }

                    int duration = Convert.ToInt32(length);
                    Note n = new Note(note, duration);
                    n.Play();
                    /*
                    if (Convert.ToInt32(length) > 0)
                    {
                        BeepUtil.BeepSpace.Beep.Beep(1000, LetToFreq(note), Convert.ToInt32(length));
                    }
                    else
                    {
                        BeepUtil.BeepSpace.Beep.Beep(1000, LetToFreq(note), 500);
                    }
                     * */
                }
            }

            /* Octave 0  1  2  3  4  5  6  7
	Note
	 C   16  33  65 131 262 523 1046 2093
	 C#  17  35  69 139 277 554 1109 2217
	 D   18  37  73 147 294 587 1175 2349
	 D#  19  39  78 155 311 622 1244 2489
	 E   21  41  82 165 330 659 1328 2637
	 F   22  44  87 175 349 698 1397 2794
	 F#  23  46  92 185 370 740 1480 2960
	 G   24  49  98 196 392 784 1568 3136
	 G#  26  52 104 208 415 831 1661 3322
	 A   27  55 110 220 440 880 1760 3520
	 A#  29  58 116 233 466 932 1865 3729
	 B   31  62 123 245 494 988 1975 3951
             */
        }

      
    }
}
