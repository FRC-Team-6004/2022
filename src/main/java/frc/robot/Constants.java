// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


  //Lemon light stuf
  public static final double KNOWN_DISTANCE = 161.3; //inches
  public static final int PIXEL_WIDTH_KNOWN = 65; //pixels
  public static final double KNOWN_TAPE_BOUND_WIDTH = 39.25; //inches
  public static final double FOCAL_LENGTH = ( KNOWN_DISTANCE * PIXEL_WIDTH_KNOWN) / KNOWN_TAPE_BOUND_WIDTH;
  public static final double GEAR_RATIO = 10.81/1.0;
  public static final double GRAVITY = 386.09; // inches/ sec ^2


  
}
