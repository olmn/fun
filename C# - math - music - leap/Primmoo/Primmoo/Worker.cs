using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel;
using System.Threading;

namespace Primmoo
{
    public class Worker
    {
        public static long LastPrimeFound = 0;
        public static long N = -1;
        public static double AvgGap = 0;
        public static long NumTwin = 0;
        public static double AvgTwinPrimeDist = 0;
        public static double PTP = 0;
        public static double PNP = 0;
        // static long LastPrime = -1;
        public static List<long> Primes = new List<long>();
        static volatile bool m_bPaused = false;
        // Public property to control worker execution
        public static bool Paused
        {
            get
            {
                return m_bPaused;
            }

            set
            {
                m_bPaused = value;
            }
        }

        protected static void AddPrime(long p)
        {
            LastPrimeFound = p;
            Primes.Add(p);
        }

        public static int Ticks = 0;
        public static void DoWork()
        {
            Thread.Sleep(10);
            if (Paused || LastPrimeFound > 0)
                return;

            if (N <= 0)
            {
                N = 2;
                AddPrime(N);
            }

            for(int i = 0; i < 100; i++)
            {
                N++;
                if (TestPrime())
                    break;
            }
        }

        static bool TestPrime()
        {
            long nHalf = Convert.ToInt64(Math.Ceiling(Convert.ToDouble(N) / Convert.ToDouble(2)));

            foreach (long p in Primes)
            {
                if (p <= nHalf)
                {
                    for (long j = nHalf; j > 1; j--)
                    {
                        if (p * j == N)
                        {
                            // not prime
                            return false;
                        }
                    }
                }

            }

            AddPrime(N);

            LastPrimeFound = N;

            if (Primes.Count % 10 == 0)
            {
                GetAvg();
            }

            return true;
        }

        static void GetAvg()
        {
            long twinPrimesFoundSoFar = 0;
            long totalTwinPrimeDistance = 0;
            long lastTwinPrime = -1;
            string gaps = "";
            long prevPrime = -1;
            long total = 0;
            for (int i = 0; i < Primes.Count; i++)
            {
                long p = Primes[i];
                if (prevPrime > 0)
                {
                    long gap = p - prevPrime;
                    if (gap == 2)
                    {
                        twinPrimesFoundSoFar += 1;
                        if (lastTwinPrime >= 0)
                        {
                            long distanceBetweenTwinPrimes = p - lastTwinPrime;
                            totalTwinPrimeDistance += distanceBetweenTwinPrimes;
                        }
                        lastTwinPrime = p;
                    }
                    if (i >= Primes.Count - 10)
                    {
                        if (gaps != "") gaps += ", ";
                        gaps += gap.ToString();
                    }
                    total += gap;
                }
                prevPrime = p;
            }
            long nonPrimes = N - Primes.Count;
            AvgGap = Convert.ToDouble(total) / Convert.ToDouble(Primes.Count);
            NumTwin = twinPrimesFoundSoFar;
            PTP = Convert.ToDouble(Primes.Count) / Convert.ToDouble(NumTwin);
            AvgTwinPrimeDist = Convert.ToDouble(totalTwinPrimeDistance) / Convert.ToDouble(twinPrimesFoundSoFar);
            PNP = Convert.ToDouble(nonPrimes) / Convert.ToDouble(Primes.Count);

            /*
            Console.WriteLine("====================");
            Console.WriteLine("Numbr of prims found: " + Primes.Count);
            Console.WriteLine(gaps);
            Console.WriteLine("Avg gap between primes: " + avg);
            Console.WriteLine("Twin primes found so far: " + twinPrimesFoundSoFar);
            Console.WriteLine("Avg distance between twin primes: " + Convert.ToDouble(totalTwinPrimeDistance) / Convert.ToDouble(twinPrimesFoundSoFar));
            Console.WriteLine("====================");
            Console.Beep(300, 100);
            System.Threading.Thread.Sleep(500);
            //ReadLine(5000);
             * */
        }

    }
}
