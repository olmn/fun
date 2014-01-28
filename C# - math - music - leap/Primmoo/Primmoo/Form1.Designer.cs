namespace Primmoo
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.pauseandplay = new System.Windows.Forms.Button();
            this.PrimeList = new System.Windows.Forms.TextBox();
            this.AvgPrimeDist = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.TwinPrimesFound = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.AvgTwinPrimeDist = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.PrimesFound = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.StatusBox = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.PTPRatio = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.PNPRatio = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // pauseandplay
            // 
            this.pauseandplay.Location = new System.Drawing.Point(469, 489);
            this.pauseandplay.Name = "pauseandplay";
            this.pauseandplay.Size = new System.Drawing.Size(75, 23);
            this.pauseandplay.TabIndex = 0;
            this.pauseandplay.Text = "Pause";
            this.pauseandplay.UseVisualStyleBackColor = true;
            this.pauseandplay.Click += new System.EventHandler(this.pauseandplay_Click);
            // 
            // PrimeList
            // 
            this.PrimeList.Location = new System.Drawing.Point(12, 12);
            this.PrimeList.Multiline = true;
            this.PrimeList.Name = "PrimeList";
            this.PrimeList.ReadOnly = true;
            this.PrimeList.Size = new System.Drawing.Size(322, 500);
            this.PrimeList.TabIndex = 1;
            // 
            // AvgPrimeDist
            // 
            this.AvgPrimeDist.Location = new System.Drawing.Point(494, 62);
            this.AvgPrimeDist.Name = "AvgPrimeDist";
            this.AvgPrimeDist.ReadOnly = true;
            this.AvgPrimeDist.Size = new System.Drawing.Size(169, 20);
            this.AvgPrimeDist.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(338, 65);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(150, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Avg Distance Between Primes";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(372, 91);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(116, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "# of TwinPrimes Found";
            // 
            // TwinPrimesFound
            // 
            this.TwinPrimesFound.Location = new System.Drawing.Point(494, 88);
            this.TwinPrimesFound.Name = "TwinPrimesFound";
            this.TwinPrimesFound.ReadOnly = true;
            this.TwinPrimesFound.Size = new System.Drawing.Size(169, 20);
            this.TwinPrimesFound.TabIndex = 4;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(365, 117);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(123, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "Avg TwinPrime Distance";
            // 
            // AvgTwinPrimeDist
            // 
            this.AvgTwinPrimeDist.Location = new System.Drawing.Point(494, 114);
            this.AvgTwinPrimeDist.Name = "AvgTwinPrimeDist";
            this.AvgTwinPrimeDist.ReadOnly = true;
            this.AvgTwinPrimeDist.Size = new System.Drawing.Size(169, 20);
            this.AvgTwinPrimeDist.TabIndex = 6;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(395, 39);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(93, 13);
            this.label4.TabIndex = 9;
            this.label4.Text = "# of Primes Found";
            // 
            // PrimesFound
            // 
            this.PrimesFound.Location = new System.Drawing.Point(494, 36);
            this.PrimesFound.Name = "PrimesFound";
            this.PrimesFound.ReadOnly = true;
            this.PrimesFound.Size = new System.Drawing.Size(169, 20);
            this.PrimesFound.TabIndex = 8;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(451, 13);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(37, 13);
            this.label5.TabIndex = 11;
            this.label5.Text = "Status";
            // 
            // StatusBox
            // 
            this.StatusBox.Location = new System.Drawing.Point(494, 10);
            this.StatusBox.Name = "StatusBox";
            this.StatusBox.ReadOnly = true;
            this.StatusBox.Size = new System.Drawing.Size(169, 20);
            this.StatusBox.TabIndex = 10;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(365, 143);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(125, 13);
            this.label6.TabIndex = 13;
            this.label6.Text = "Prime to TwinPrime Ratio";
            // 
            // PTPRatio
            // 
            this.PTPRatio.Location = new System.Drawing.Point(494, 140);
            this.PTPRatio.Name = "PTPRatio";
            this.PTPRatio.ReadOnly = true;
            this.PTPRatio.Size = new System.Drawing.Size(169, 20);
            this.PTPRatio.TabIndex = 12;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(365, 169);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(125, 13);
            this.label7.TabIndex = 15;
            this.label7.Text = "Non-Prime to Prime Ratio";
            // 
            // PNPRatio
            // 
            this.PNPRatio.Location = new System.Drawing.Point(494, 166);
            this.PNPRatio.Name = "PNPRatio";
            this.PNPRatio.ReadOnly = true;
            this.PNPRatio.Size = new System.Drawing.Size(169, 20);
            this.PNPRatio.TabIndex = 14;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(675, 524);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.PNPRatio);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.PTPRatio);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.StatusBox);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.PrimesFound);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.AvgTwinPrimeDist);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.TwinPrimesFound);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.AvgPrimeDist);
            this.Controls.Add(this.PrimeList);
            this.Controls.Add(this.pauseandplay);
            this.Name = "Form1";
            this.Text = "Primoo 3000";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button pauseandplay;
        private System.Windows.Forms.TextBox PrimeList;
        private System.Windows.Forms.TextBox AvgPrimeDist;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox TwinPrimesFound;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox AvgTwinPrimeDist;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox PrimesFound;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox StatusBox;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox PTPRatio;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox PNPRatio;
    }
}

