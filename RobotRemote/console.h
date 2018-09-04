#ifndef __CONSOLE_H__
#define __CONSOLE_H__

void conInit(void);
void conScroll(void);
int conGetKey(void);
void conReset(void);

#define CKEY_DOWN 258
#define CKEY_UP 259
#define CKEY_LEFT 260
#define CKEY_RIGHT 261

#define CKEY_ENTER 10
#define CKEY_SPACE 32
#define CKEY_BACKSPACE 263
#define CKEY_ESC 27
#define CKEY_DELETE 330
#define CKEY_INSERT 331
#define CKEY_HOME 262
#define CKEY_END 360
#define CKEY_PAGEDOWN 338
#define CKEY_PAGEUP 339

#define CKEY_F1 265
#define CKEY_F2 266
#define CKEY_F3 267
#define CKEY_F4 268
#define CKEY_F5 269
#define CKEY_F6 270
#define CKEY_F7 271
#define CKEY_F8 272
#define CKEY_F9 273
#define CKEY_F10 274
#define CKEY_F11 275
#define CKEY_F12 276

#endif
