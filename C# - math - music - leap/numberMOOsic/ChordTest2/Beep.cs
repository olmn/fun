using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Media;

namespace ChordTest2
{
    public class Beep
    {
        public static void GoAheadAndBeep(int Amplitude, int Frequency, int Duration)
        {
            double a = ((Amplitude * 2 ^ 15) / 1000) - 1;
            double deltaFt =  2 * Math.PI * Frequency / 44100;
            int eek = Convert.ToInt32(Math.Floor(Convert.ToDouble(Duration) / Convert.ToDouble(10)));
            int samples = 441 * eek;
            int samplesOld = 441 * Duration / 10;
            int bytes = samples * 4;
            int [] hdr = {0x46464952, 36 + bytes, 0x45564157, 0x20746D66, 16, 0x20001, 44100, 176400, 0x100004, 0x61746164, bytes};
            using(MemoryStream ms = new MemoryStream(44 + bytes))
            {
                using (BinaryWriter bw = new BinaryWriter(ms))
                {
                    for(int i = 0; i < hdr.Length; i++)
                    {
                        bw.Write(hdr[i]);
                    }
                    for(int t = 0; t < samples; t++)
                    {
                        short sample = Convert.ToInt16(a * Math.Sin(deltaFt * t));
                        bw.Write(sample);
                        bw.Write(sample);
                    }
                    bw.Flush();
                    ms.Seek(0, SeekOrigin.Begin);
                    using(SoundPlayer sp = new SoundPlayer(ms))
                    {
                        sp.PlaySync();
                    }
                }
            }
        }
    }
}
