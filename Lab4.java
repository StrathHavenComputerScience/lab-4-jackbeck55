public class Lab4
{
    public static void turnRight()
    {
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void turnAround()
    {
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void backUp()
    {
        turnAround();
        Robot.move();
        turnAround();
    }

    public static void completeBars()
    {
        //before: all of the bars haven't been filled
        //after: all the bars have been filled except for the last one
        while (Robot.frontIsClear())
        {
            completeOneBar();
        }        
        //before: Robot is facing one of the bars that it needs to darken
        //after: Robot has darkened the bar to the where it should be extended to and is now back at the bottom facing the next line of squares to move forward one
        //the purpose of this statement was because in my orginale statment the robot would move forward at the end this slight changed statement allows the robot to fill in the last bar and stop without an error message saying that the robot can't move forward
        if (!Robot.onDark())
        {
            Robot.turnLeft();
            while (!Robot.onDark())
            {
                Robot.makeDark();
                Robot.move();
            }
            turnAround();
            Robot.move();
            while (Robot.frontIsClear())
            {
                Robot.move();
            }
            Robot.turnLeft();  

        }        
    }

    public static void completeOneBar()
    {
        //before: Robot is facing one of the bars that it needs to darkenif (!Robot.onDark())
        //after:Robot has darkened the bar to the where it should be extended to and moved back to the bottom and went on to the next row
        if (!Robot.onDark())
        {
            Robot.turnLeft();
            while (!Robot.onDark())
            {
                Robot.makeDark();
                Robot.move();
            }
            turnAround();
            Robot.move();
            while (Robot.frontIsClear())
            {
                Robot.move();
            }
            Robot.turnLeft();  
            Robot.move();                
        }
        else
        {
            Robot.move(); 
        }
    }

    public static void testCompleteBars1()
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.05);
        completeBars();
    }

    public static void testCompleteBars2()
    {
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void combinePiles()
    {
        //before: the pile on the left has not been added to the one on the right
        //after: the pile on the left has been added to the one on the right
        while (Robot.frontIsClear())
        {
            mainPileCombine();
        }

    }

    public static void mainPileCombine()
    {
        //before: none of the blocks have been added to the new pile
        //after: the bottom most block on the left has been changed and out on the top most block on the right hand side 
        if (Robot.onDark())
        {
            //this first part of the if statement is only for when the robot intially is on a dark block. If it isn't it will move on to the else
            Robot.makeLight();
            goToRightMostSquare();
            while (Robot.onDark())
            {
                Robot.move();
            }
            Robot.makeDark();
            goBackToLeftMostSquare();
        }
        else
        {
            //this is for when the robot is moved to the left most corner and isn't on a dark block. It finds a new dark block change and then moves it to the right column and then goes back to the left most position.
            while (!(Robot.onDark()) && (Robot.frontIsClear()))
            {
                Robot.move();
            }
            if (!Robot.onDark())
            {
                robotStop();
            }
            else
            {
                Robot.makeLight();
                goToRightMostSquare();
                while (Robot.onDark())
                {
                    Robot.move();
                }
                Robot.makeDark();
                goBackToLeftMostSquare();
            }
        }
    }

    public static void robotStop()
    {
        //before: Robot is heading towards the top of the two columns
        //after: stops the robot from hitting the top of the two columns and sends it back to the bottom left colomn
        turnAround();
        while (Robot.frontIsClear())
        {
            Robot.move();
        }
    }

    public static void goToRightMostSquare()
    {
        //before: Robot is on any of the blocks on the left most column 
        //after: Robot is on the bottom right most block
        turnRight();
        Robot.move();
        turnRight();
        while (Robot.frontIsClear())
        {
            Robot.move();
        }
        turnAround();
    }

    public static void goBackToLeftMostSquare()
    {
        //before: Robot is on any of the blocks on the right most column
        //after: Robot is on the bottom left most blcok
        Robot.turnLeft();
        Robot.move();
        Robot.turnLeft();
        while (Robot.frontIsClear())
        {
            Robot.move();
        }
        turnAround();
    }

    public static void testCombinePiles1()
    {
        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void testCombinePiles2()
    {
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void connectDots()
    {
        //before: none of the dots have been connected
        //after: all of the dots have been connected and the robot has stopped
        while (canRobotMoveForward())
        {
            connectDotsMain();
        }
    }

    public static void connectDotsMain()
    {

        //before: the block between the first and third has not been connected
        //after: the block between the first and third has been connected and it has checked to see if the robot should move forward and if not whether it should turn right or left 
        while (Robot.onDark())
        {
            Robot.move();  
        }
        while (!Robot.onDark())
        {
            Robot.makeDark();
            Robot.move();
        }
        if (!canRobotMoveForward())
        {
            if (doITurnRight())
            {
                turnRight();
            }
            else
            {
                Robot.turnLeft();
            }
        }
    }

    public static boolean doITurnRight()
    {
        //before: Robot is facing three blocks wondering if it should turn right
        //after: Robot has the knowledge of whether it should turn right or not and is in its orginal position
        turnRight();
        Robot.move();
        Robot.move();
        if (Robot.onDark())
        {
            turnAround();
            Robot.move();
            Robot.move();
            turnRight();
            return true;
        }
        else
        {
            turnAround();
            Robot.move();
            Robot.move();
            turnRight();
            return false;
        }
    }

    public static boolean canRobotMoveForward()
    {
        //before: Robot is facing three blocks and is wondering if it should move forward
        //after: Robot has the knowledge of whether it should move forward or not and has returned to its orginal position
        Robot.move();
        Robot.move();
        if (Robot.onDark())
        {
            turnAround();
            Robot.move();
            Robot.move();
            turnAround();
            return true;
        }
        else
        {
            turnAround();
            Robot.move();
            Robot.move();
            turnAround();
            return false;
        }
    }

    public static void testConnectDots1()
    {
        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        connectDots();
    }

    public static void testConnectDots2()
    {
        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        connectDots();
    }
}
