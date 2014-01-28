using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using BeepUtil.BeepSpace;


namespace ChordTest2
{
    public class Note
    {
        public int Frequency = 0;
        public int Amplitude = 1000;
        public int Duration = 1000;
        public bool Async = false;

        public static void PlayNote(string note, int duration = 500)
        {
            BeepUtil.BeepSpace.Beep.Beep(1000, LetToFreq(note), duration, false);

            /*
            Note n = new Note(note, duration);
            n.Async = async;
            n.Play();
             * */
        }

        public Note(string note, int duration)
        {
            Frequency = LetToFreq(note);
            if(duration > 0)
                Duration = duration;
        }

        public static void Chord(string notes)
        {

            BeepUtil.BeepSpace.Beep.Chord(false, 1000, 1000, LetToFreq(notes[0].ToString()), LetToFreq(notes[1].ToString()), LetToFreq(notes[2].ToString()));
        }

        public void Play()
        {
            Thread trd = new Thread(new ThreadStart(this.PlayThread));
            trd.IsBackground = true;
            trd.Start();
        }

        private void PlayThread()
        {
            BeepUtil.BeepSpace.Beep.Beep(Amplitude, Frequency, Duration, false);
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

        protected static int LetToFreq(string s)
        {
            int octave = 6;
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
            for (int noteIndex = 0; noteIndex <= 12; noteIndex++)
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
