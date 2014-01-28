using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using Leap;

namespace LeapTest
{
    class LeapListener : Listener
    {
        private Object thisLock = new Object();

        private void SafeWriteLine(String line)
        {
            lock (thisLock)
            {
//                Console.Clear();
                Console.WriteLine(line);
            }
        }

        private int ToInt(object val)
        {
            if (val == null)
                return 0;

            return Convert.ToInt32(Math.Round(Convert.ToDecimal(val.ToString())));
        }

        public override void OnInit(Controller controller)
        {
            SafeWriteLine("Initialized");
        }

        public override void OnConnect(Controller controller)
        {
            SafeWriteLine("Connected");
            controller.EnableGesture(Gesture.GestureType.TYPECIRCLE);
            controller.EnableGesture(Gesture.GestureType.TYPEKEYTAP);
            controller.EnableGesture(Gesture.GestureType.TYPESCREENTAP);
            controller.EnableGesture(Gesture.GestureType.TYPESWIPE);
        }

        public override void OnDisconnect(Controller controller)
        {
            //Note: not dispatched when running in a debugger.
            SafeWriteLine("Disconnected");
        }

        public override void OnExit(Controller controller)
        {
            SafeWriteLine("Exited");
        }

        public void LeftHand(Frame frame)
        {
            if (!frame.Hands.IsEmpty)
            {
                // Get the first hand
                Hand hand = frame.Hands[0];
                Vector avgPos = Vector.Zero;

                #region Fingers
                // Check if the hand has any fingers
                FingerList fingers = hand.Fingers;
                if (!fingers.IsEmpty)
                {
                    // Calculate the hand's average finger tip position
                    foreach (Finger finger in fingers)
                    {
                        avgPos += finger.TipPosition;
                    }
                    avgPos /= fingers.Count;
                    // change: SafeWriteLine("Hand has " + fingers.Count  + " fingers, average finger tip position: " + avgPos);
                }
                #endregion

                // Get the hand's normal vector and direction
                Vector normal = hand.PalmNormal;
                Vector direction = hand.Direction;

                
                int vPos = Convert.ToInt32(hand.PalmPosition.y);
                vPos -= 150;
                if (vPos <= 0)
                    vPos = 1;

                int noteIndex = vPos * 28 / 250;

                int octave = noteIndex / 7;
                int note = noteIndex % 7;

                if (octave > 11)
                    octave = 11;
                if (octave < 3)
                    octave = 3;

                Organ.SetChord(note, octave);

                Console.WriteLine("Chord (" + hand.PalmPosition.y +"): " + octave + ":" + note);
            }
        }

        public void RightHand(Frame frame)
        {
            if (frame.Hands.Count > 1)
            {
                // Get the first hand
                Hand hand = frame.Hands[1];
                Vector avgPos = Vector.Zero;

                FingerList fingers = hand.Fingers;
                if (!fingers.IsEmpty)
                {
                    Finger finger = fingers[0];

                    int vPos = Convert.ToInt32(finger.TipPosition.y);
                    vPos -= 150;
                    if (vPos <= 0)
                        vPos = 1;
                    int noteIndex = vPos * 28 / 250;
                    int octave = noteIndex / 7;

                    if (octave > 11)
                        octave = 11;
                    if (octave < 8)
                        octave = 8;

                    int hPos = Convert.ToInt32(finger.TipPosition.x);
                    hPos -= 50;
                    int note = hPos * 7 / 300;
                    if(note <= 0)
                        note = 1;
                    if(note > 7)
                        note = 7;

                    Console.WriteLine("fingertip: (" + finger.TipPosition.x + "," + finger.TipPosition.y + "): " + note);

                    Organ.SetNoteToPlay(note, octave);
                    // Organ.PlayNote(300, note, octave);

                }              
            }
        }

        public override void OnFrame(Controller controller)
        {
            // Get the most recent frame and report some basic information
            Frame frame = controller.Frame();

            LeftHand(frame);
            RightHand(frame);

            /*
                    // change:             SafeWriteLine("Frame id: " + frame.Id
                        + ", timestamp: " + frame.Timestamp
                        + ", hands: " + frame.Hands.Count
                        + ", fingers: " + frame.Fingers.Count
                        + ", tools: " + frame.Tools.Count
                        + ", gestures: " + frame.Gestures().Count);
            */
            if (!frame.Hands.IsEmpty)
            {
                // Get the first hand
                Hand hand = frame.Hands[0];
                Vector avgPos = Vector.Zero;

                // Check if the hand has any fingers
                FingerList fingers = hand.Fingers;
                if (!fingers.IsEmpty)
                {
                    // Calculate the hand's average finger tip position
                    foreach (Finger finger in fingers)
                    {
                        avgPos += finger.TipPosition;
                    }
                    avgPos /= fingers.Count;
                    // change: SafeWriteLine("Hand has " + fingers.Count  + " fingers, average finger tip position: " + avgPos);
                }

                // Get the hand's normal vector and direction
                Vector normal = hand.PalmNormal;
                Vector direction = hand.Direction;

                /*
                // Get the hand's sphere radius and palm position  palm position: " + ToInt(hand.PalmPosition)
                SafeWriteLine("Hand sphere radius: " + ToInt(hand.SphereRadius.ToString("n2"))
                            + "\r\nFingers detected: " + fingers.Count
                            + "\r\nAvg finger pos: " + avgPos
                            + "\r\nHand pitch: " + ToInt(direction.Pitch * 180.0f / (float)Math.PI) + ", "
                            + "roll: " + ToInt(normal.Roll * 180.0f / (float)Math.PI) + ", "
                            + "yaw: " + ToInt(direction.Yaw * 180.0f / (float)Math.PI) + " ");
                 * */
            }

            #region Gestures

            /*
            // Get gestures
            GestureList gestures = frame.Gestures();
            for (int i = 0; i < gestures.Count; i++)
            {
                Gesture gesture = gestures[i];

                switch (gesture.Type)
                {
                    case Gesture.GestureType.TYPECIRCLE:
                        CircleGesture circle = new CircleGesture(gesture);

                        // Calculate clock direction using the angle between circle normal and pointable
                        String clockwiseness;
                        if (circle.Pointable.Direction.AngleTo(circle.Normal) <= Math.PI / 4)
                        {
                            //Clockwise if angle is less than 90 degrees
                            clockwiseness = "clockwise";
                        }
                        else
                        {
                            clockwiseness = "counterclockwise";
                        }

                        float sweptAngle = 0;

                        // Calculate angle swept since last frame
                        if (circle.State != Gesture.GestureState.STATESTART)
                        {
                            CircleGesture previousUpdate = new CircleGesture(controller.Frame(1).Gesture(circle.Id));
                            sweptAngle = (circle.Progress - previousUpdate.Progress) * 360;
                        }

                        SafeWriteLine("Circle id: " + circle.Id
                                       + ", " + circle.State
                                       + ", progress: " + circle.Progress
                                       + ", radius: " + circle.Radius
                                       + ", angle: " + sweptAngle
                                       + ", " + clockwiseness);
                        break;
                    case Gesture.GestureType.TYPESWIPE:
                        SwipeGesture swipe = new SwipeGesture(gesture);
                        SafeWriteLine("Swipe id: " + swipe.Id
                                       + ", " + swipe.State
                                       + ", position: " + swipe.Position
                                       + ", direction: " + swipe.Direction
                                       + ", speed: " + swipe.Speed);
                        break;
                    case Gesture.GestureType.TYPEKEYTAP:
                        KeyTapGesture keytap = new KeyTapGesture(gesture);
                        SafeWriteLine("Tap id: " + keytap.Id
                                       + ", " + keytap.State
                                       + ", position: " + keytap.Position
                                       + ", direction: " + keytap.Direction);
                        break;
                    case Gesture.GestureType.TYPESCREENTAP:
                        ScreenTapGesture screentap = new ScreenTapGesture(gesture);
                        SafeWriteLine("Tap id: " + screentap.Id
                                       + ", " + screentap.State
                                       + ", position: " + screentap.Position
                                       + ", direction: " + screentap.Direction);
                        break;
                    default:
                        SafeWriteLine("Unknown gesture type.");
                        break;
                }
            }
            if (!frame.Hands.IsEmpty || !frame.Gestures().IsEmpty)
            {
                SafeWriteLine("");
            }
             * */

            #endregion



        }
    }
}
