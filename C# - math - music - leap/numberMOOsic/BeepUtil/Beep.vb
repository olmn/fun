Imports System.IO
Imports System.Media

Namespace BeepSpace

    Public Class Beep
        Shared Sub Beep(ByVal Amplitude As Integer, _
                 ByVal Frequency As Integer, _
                 ByVal Duration As Integer, _
                 ByVal Async As Boolean)
            Dim A As Double = ((Amplitude * 2 ^ 15) / 1000) - 1
            Dim DeltaFT As Double = 2 * Math.PI * Frequency / 44100

            Dim Samples As Integer = 441 * Duration \ 10
            Dim Bytes As Integer = Samples * 4
            Dim Hdr() As Integer = {&H46464952, 36 + Bytes, &H45564157, _
                                    &H20746D66, 16, &H20001, 44100, _
                                     176400, &H100004, &H61746164, Bytes}
            Using MS As New MemoryStream(44 + Bytes)
                Using BW As New BinaryWriter(MS)
                    For I As Integer = 0 To Hdr.Length - 1
                        BW.Write(Hdr(I))
                    Next
                    For T As Integer = 0 To Samples - 1
                        Dim Sample As Short = CShort(A * Math.Sin(DeltaFT * T))
                        BW.Write(Sample)
                        BW.Write(Sample)
                    Next
                    BW.Flush()
                    MS.Seek(0, SeekOrigin.Begin)
                    Using SP As New SoundPlayer(MS)
                        If Async Then
                            SP.Play()
                        Else
                            SP.PlaySync()
                        End If
                    End Using
                End Using
            End Using
        End Sub

        Shared Function GetSample(ByVal T As Integer, ByVal A As Double, ByVal DeltaFt As Double)

            ' Dim DeltaFT As Double = 2 * Math.PI * Freq / 44100

            GetSample = CShort(T) ' CShort(A * Math.Sin(DeltaFT * T))

        End Function

        Shared Sub Chord(ByVal Async As Boolean, ByVal Amplitude As Integer, ByVal Duration As Integer, ByVal Freq1 As Integer, ByVal Freq2 As Integer, ByVal Freq3 As Integer)
            Dim A As Double = ((Amplitude * 2 ^ 15) / 1000) - 1
            ' Dim DeltaFT As Double = 2 * Math.PI * Frequency / 44100

            Dim DeltaFT1 As Double = 2 * Math.PI * Freq1 / 44100
            Dim DeltaFT2 As Double = 2 * Math.PI * Freq2 / 44100
            Dim DeltaFT3 As Double = 2 * Math.PI * Freq3 / 44100

            Dim Samples As Integer = 441 * Duration \ 10
            Dim Bytes As Integer = Samples * 6
            Dim Hdr() As Integer = {&H46464952, 36 + Bytes, &H45564157, _
                                    &H20746D66, 16, &H20001, 44100, _
                                     176400, &H100004, &H61746164, Bytes}
            Using MS As New MemoryStream(44 + Bytes)
                Using BW As New BinaryWriter(MS)
                    For I As Integer = 0 To Hdr.Length - 1
                        BW.Write(Hdr(I))
                    Next
                    For T As Integer = 0 To Samples - 1
                        BW.Write(CShort(A * Math.Sin(DeltaFT1 * T)))
                        BW.Write(CShort(A * Math.Sin(DeltaFT2 * T)))
                        BW.Write(CShort(A * Math.Sin(DeltaFT3 * T)))
                    Next
                    BW.Flush()
                    MS.Seek(0, SeekOrigin.Begin)
                    Using SP As New SoundPlayer(MS)
                        If Async Then
                            SP.Play()
                        Else
                            SP.PlaySync()
                        End If
                    End Using
                End Using
            End Using
        End Sub

        Shared Sub ChordAndNote(ByVal Duration As Integer, ByVal FreqN As Integer, ByVal Freq1 As Integer, ByVal Freq2 As Integer, ByVal Freq3 As Integer)

            Dim AmplitudeForChord As Integer = 500
            Dim AmplitudeForNote As Integer = 1000
            Dim A As Double = ((AmplitudeForChord * 2 ^ 15) / 1000) - 1
            Dim Anote As Double = ((AmplitudeForNote * 2 ^ 15) / 1000) - 1
            ' Dim DeltaFT As Double = 2 * Math.PI * Frequency / 44100

            Dim DeltaFT1 As Double = 2 * Math.PI * Freq1 / 44100
            Dim DeltaFT2 As Double = 2 * Math.PI * Freq2 / 44100
            Dim DeltaFT3 As Double = 2 * Math.PI * Freq3 / 44100
            Dim DeltaFTNote As Double = 2 * Math.PI * FreqN / 44100

            Dim Samples As Integer = 441 * Duration \ 10
            Dim Bytes As Integer = Samples * 8
            Dim Hdr() As Integer = {&H46464952, 36 + Bytes, &H45564157, _
                                    &H20746D66, 16, &H20001, 44100, _
                                     176400, &H100004, &H61746164, Bytes}
            Using MS As New MemoryStream(44 + Bytes)
                Using BW As New BinaryWriter(MS)
                    For I As Integer = 0 To Hdr.Length - 1
                        BW.Write(Hdr(I))
                    Next
                    For T As Integer = 0 To Samples - 1
                        BW.Write(CShort(A * Math.Sin(DeltaFT1 * T)))
                        BW.Write(CShort(A * Math.Sin(DeltaFT2 * T)))
                        BW.Write(CShort(A * Math.Sin(DeltaFT3 * T)))
                        BW.Write(CShort(Anote * Math.Sin(DeltaFTNote * T)))
                    Next
                    BW.Flush()
                    MS.Seek(0, SeekOrigin.Begin)
                    Using SP As New SoundPlayer(MS)
                        SP.Play()
                    End Using
                End Using
            End Using
        End Sub
    End Class

End Namespace
