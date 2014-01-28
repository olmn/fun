using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace LeapTest
{
    public static class Organ
    {
        private static int Freq1;
        private static int Freq2;
        private static int Freq3;

        private static int NoteToPlay = -1;

        private static Object theLock = new Object();

        public static void StartPlaying()
        {
            Thread trd = new Thread(new ThreadStart(PlayThread));
            trd.IsBackground = true;
            trd.Start();
        }

        public static void PlayNote(int duration, int noteIndex, int octave)
        {
            if (noteIndex >= 0 && noteIndex < chordNotes.Length)
            {
                string note = chordNotes[noteIndex, 0].ToString();
                int freq = LetToFreq(note, octave);
                BeepUtil.BeepSpace.Beep.Beep(1000, freq, duration, false);
            }
        }

        public static void SetNoteToPlay(int n, int octave = 6)
        {
            lock (theLock)
            {
                NoteToPlay = n;
            }
        }

        public static void SetChord(string c, int octave = 6)
        {
            lock (theLock)
            {
                Freq1 = LetToFreq(c[0].ToString(), octave);
                Freq2 = LetToFreq(c[1].ToString(), octave);
                Freq3 = LetToFreq(c[2].ToString(), octave);
            }
        }

        public static void SetChord(int note, int octave = 6)
        {
            if (note >= 0 && note < chordNotes.Length)
            {
                string note1 = "";
                string note2 = "";
                string note3 = "";
                string chord = chordNotes[note, 0];
                if (GetNotesForChord(chord, ref note1, ref note2, ref note3))
                {
                    lock (theLock)
                    {
                        Freq1 = LetToFreq(note1, octave);
                        Freq2 = LetToFreq(note2, octave);
                        Freq3 = LetToFreq(note3, octave);
                    }
                }
            }
        }


        private static void PlayThread()
        {
            while (true)
            {
                int duration = 250;
                lock (theLock)
                {
                    int noteFreq = 0;
                    if (NoteToPlay >= 0)
                    {
                        if(NoteToPlay < chordNotes.Length)
                        {
                            noteFreq = LetToFreq(chordNotes[NoteToPlay,0]);
                        }
                        NoteToPlay = -1;
                    }

                    if(noteFreq > 0)
                        BeepUtil.BeepSpace.Beep.ChordAndNote(duration, noteFreq, Freq1, Freq2, Freq3);
                    else
                        BeepUtil.BeepSpace.Beep.Chord(true, 1000, duration, Freq1, Freq2, Freq3);
                    System.Threading.Thread.Sleep(duration);
                }
            }
        }

        static string[,] chordNotes = {
{"C","C","E","G"},
{"C♯","C♯","F","G♯"},
{"D♭","D♭","F","A♭"},
{"D","D","F♯","A"},
{"D♯","D♯","G","A♯"},
{"E♭","E♭","G","B♭"},
{"E","E","G♯","B"},
{"F","F","A","C"},
{"F♯","F♯","A♯","C♯"},
{"G♭","G♭","B♭","D♭"},
{"G","G","B","D"},
{"G♯","G♯","C","D♯"},
{"A♭","A♭","C","E♭"},
{"A","A","C♯","E"},
{"A♯","A♯","C","F"},
{"B♭","B♭","D","F"},
{"B","B","D♯","F♯"}

                                           };
        public static bool GetNotesForChord(string chord, ref string note1, ref string note2, ref string note3)
        {
            chord = chord.ToUpper();
            for (int i = 0; i < chordNotes.Length; i++)
            {
                if (chordNotes[i, 0] == chord)
                {
                    note1 = chordNotes[i, 1];
                    note2 = chordNotes[i, 2];
                    note3 = chordNotes[i, 3];
                    return true;
                }
            }

            return false;
        }

        static string[,] noteFrequencies = {
{"A","0","27","55","110","220","440","880","1760"},
{"A#","0","29","58","116","233","466","932","1865"},
{"B","0","31","62","123","245","494","988","1975"},
{"C","16","33","65","131","262","523","1046","2093"},
{"C#","17","35","69","139","277","554","1109","2217"},
{"D","18","37","73","147","294","587","1175","2349"},
{"D#","19","39","78","155","311","622","1244","2489"},
{"E","21","41","82","165","330","659","1328","2637"},
{"F","22","44","87","175","349","698","1397","2794"},
{"F#","23","46","92","185","370","740","1480","2960"},
{"G","24","49","98","196","392","784","1568","3136"},
{"G#","26","52","104","208","415","831","1661","3322"}
                                           };

        /*static string[,] noteFrequencies = {
{"C","16","33","65","131","262","523","1046","2093"},
{"C#","17","35","69","139","277","554","1109","2217"},
{"D","18","37","73","147","294","587","1175","2349"},
{"D#","19","39","78","155","311","622","1244","2489"},
{"E","21","41","82","165","330","659","1328","2637"},
{"F","22","44","87","175","349","698","1397","2794"},
{"F#","23","46","92","185","370","740","1480","2960"},
{"G","24","49","98","196","392","784","1568","3136"},
{"G#","26","52","104","208","415","831","1661","3322"},
{"A","27","55","110","220","440","880","1760","3520"},
{"A#","29","58","116","233","466","932","1865","3729"},
{"B","31","62","123","245","494","988","1975","3951"}
                                           };
         */

        public static int LetToFreq(string s, int octave = 6)
        {
            if (s.StartsWith("+"))
            {
                if (s.StartsWith("++"))
                {
                    octave = 7;
                    s = s.Substring(2);
                }
                else
                {
                    octave = 6;
                    s = s.Substring(1);
                }
            }
            if (s.StartsWith("-"))
            {
                if (s.StartsWith("--"))
                {
                    octave = 3;
                    s = s.Substring(2);
                }
                else
                {
                    octave = 4;
                    s = s.Substring(1);
                }
            }
            s = s.ToUpper();
            for (int noteIndex = 0; noteIndex < 12; noteIndex++)
            {
                if (noteFrequencies[noteIndex, 0] == s)
                {
                    string freq = noteFrequencies[noteIndex, octave];
                    return Convert.ToInt32(freq);
                }
            }

            return -1;

        }
    }

}
