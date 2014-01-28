using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Threading;

namespace Primmoo
{
    public partial class Form1 : Form
    {
        BackgroundWorker bw = null;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            StartWorker();               
        }

        private void StartWorker()
        {
            bw = new BackgroundWorker();

            // this allows our worker to report progress during work
            bw.WorkerReportsProgress = true;

            // what to do in the background thread
            bw.DoWork += new DoWorkEventHandler(
            delegate(object o, DoWorkEventArgs args)
            {
                BackgroundWorker b = o as BackgroundWorker;

                while (true)
                {
                    Worker.DoWork();
                    b.ReportProgress(1);
                }

            });

            // what to do when progress changed (update the progress bar for example)
            bw.ProgressChanged += new ProgressChangedEventHandler(
            delegate(object o, ProgressChangedEventArgs args)
            {
                if (Worker.Paused)
                    StatusBox.Text = "Paused";
                else
                {
                    StatusBox.Text = "Running!";
                    if (Worker.LastPrimeFound > 0)
                    {
                        PrimeList.AppendText(Worker.LastPrimeFound.ToString() + ", ");
                        Worker.LastPrimeFound = 0;
                        PrimesFound.Text = Worker.Primes.Count.ToString();
                        AvgPrimeDist.Text = Worker.AvgGap.ToString();
                        TwinPrimesFound.Text = Worker.NumTwin.ToString();
                        PTPRatio.Text = Worker.PTP.ToString();
                        PNPRatio.Text = Worker.PNP.ToString();
                        AvgTwinPrimeDist.Text = Worker.AvgTwinPrimeDist.ToString();
                    }
                }
            });

            // what to do when worker completes its task (notify the user)
            bw.RunWorkerCompleted += new RunWorkerCompletedEventHandler(
            delegate(object o, RunWorkerCompletedEventArgs args)
            {
                StatusBox.Text = "Finished!";
            });

            bw.RunWorkerAsync();
        }
    
        private void pauseandplay_Click(object sender, EventArgs e)
        {
            if (pauseandplay.Text == "Pause")
            {
                Worker.Paused = true;
                pauseandplay.Text = "Start";
            }
            else
            {
                Worker.Paused = false;
                pauseandplay.Text = "Pause";
            }
        }
    }
}
