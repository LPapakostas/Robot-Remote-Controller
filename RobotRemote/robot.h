#ifndef __ROBOT_H__
#define __ROBOT_H__

#undef TRUE
#define TRUE 1
#undef FALSE
#define FALSE 0

typedef unsigned char bool_t;

#define REQ_ERROR -2
#define REQ_UNKNOWN -1

#define REQ_STOP 1
#define REQ_MOVE 2
#define REQ_STEP 3
#define REQ_TURNLEFT 4
#define REQ_TURNRIGHT 5
#define REQ_RESET 6
#define REQ_QUIT 7
#define REQ_SENSE 8
#define REQ_STATE 9
#define REQ_ROBOTINIT 10
#define REQ_ROBOTDESTROY 11
#define REQ_SETMODE 12
#define REQ_GOHOME 13
#define REQ_LOADLABYRINTH 14
#define REQ_EXITFOUND 15

#define RSP_ERROR -1
#define RSP_OK 1
#define RSP_SENSE 2
#define RSP_STATE 3
#define RSP_EXITFOUND 4

#define ROBOT_NAME_LENGHT 16

#define STATE_STOPED 1
#define STATE_MOVING 2
#define STATE_STEPING 3

#define MODE_MANUAL 0
#define MODE_AUTO 1
#define MODE_DEFAULT MODE_MANUAL

#define NO_WALL 1000

struct sensor {
	bool_t wall;
	int distance;
};

struct sensors {
	struct sensor front;
	struct sensor left;
	struct sensor right;
};

int labConnect(char *server_addr, int server_port);
int labLoadLabyrinth(char *fname);
int labInsertRobot(char *name);
int labDestroyRobot(void);
int labSetMode(char mode);
int labDisconnect(void);

int robotGoAhead(void);
int robotStep(int steps);
int robotTurnLeft(void);
int robotTurnRight(void);
int robotStop(void);
int robotGetSensors(struct sensors *sens);
int robotViewFront(void);
int robotViewLeft(void);
int robotViewRight(void);
int robotGetState(void);
int robotExitFound(void);
int robotGoHome(void);

#define robotGetView robotViewFront

#endif
