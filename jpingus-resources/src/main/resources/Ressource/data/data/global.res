// Obsolete file, only here for backward compatibility!
// 

section Liquid
{
	water_cmap  = ../images/liquids/water_cmap.png      (type=surface, x=0, y=0, width=1,  height=64);

	water       = ../images/liquids/water.png      (type=surface, x=0, y=0, width=32,  height=32, array=16x1);
	water2      = ../images/liquids/water2.png     (type=surface, x=0, y=0, width=64,  height=64, array=12x1);
	water3      = ../images/liquids/water3.png     (type=surface, x=0, y=0, width=64,  height=64, array=8x1);
	water4      = ../images/liquids/water4.png     (type=surface, x=0, y=0, width=64,  height=64, array=8x1);
	water5      = ../images/liquids/water3.png     (type=surface, x=0, y=0, width=64,  height=64, array=4x1);
	swater      = ../images/liquids/swater.png     (type=surface, x=0, y=0, width=64,  height=64, array=4x1);
	lava        = ../images/liquids/lava.png       (type=surface, x=0, y=0, width=64,  height=64, array=12x1);
	slime        = ../images/liquids/slime.png       (type=surface, x=0, y=0, width=64,  height=64, array=12x1);
}

section Hotspots
{
        engine = ../images/hotspots/space/engine.png (type=surface, x=0, y=0, width=108, height=166, array=4x1);

	left_arrow = ../images/groundpieces/ground/signposts/arrow_left.png (type=surface);
	right_arrow = ../images/groundpieces/ground/signposts/arrow_right.png (type=surface);

	up_arrow = ../images/groundpieces/ground/signposts/arrow_up.png (type=surface);
	down_arrow = ../images/groundpieces/ground/signposts/arrow_down.png (type=surface);

	danger      = ../images/groundpieces/ground/signposts/danger.png (type=surface, x=0, y=0, width=215, height=121);
//	crystal     = ../images/hotspots/crystal.png    (type=surface, x=0, y=0, width=60,  height=109);
	colum       = ../images/groundpieces/ground/misc/column.png      (type=surface);
	colum_flat  = ../images/groundpieces/ground/misc/column_flat.png (type=surface);

	colum_piece1 = ../images/groundpieces/ground/desert/column_piece1.png      (type=surface, x=0, y=0, width=103, height=66);
	colum_piece2 = ../images/groundpieces/ground/desert/column_piece2.png      (type=surface, x=0, y=0, width=128, height=66);
	colum_piece3 = ../images/groundpieces/ground/desert/column_piece3.png      (type=surface, x=0, y=0, width=128, height=111);

	colum_pice1 = ../images/groundpieces/ground/desert/column_piece1.png      (type=surface, x=0, y=0, width=103, height=66);
	colum_pice2 = ../images/groundpieces/ground/desert/column_piece2.png      (type=surface, x=0, y=0, width=128, height=66);
	colum_pice3 = ../images/groundpieces/ground/desert/column_piece3.png      (type=surface, x=0, y=0, width=128, height=111);

//	bridge     = ../images/hotspots/bridge.png    (type=surface, x=0, y=0, width=150,  height=58);

	stone0      = ../images/groundpieces/ground/misc/stone0.png     (type=surface, x=0, y=0, width=106,  height=91);
	stone1      = ../images/groundpieces/ground/misc/stone1.png     (type=surface, x=0, y=0, width=73,  height=21);
	stone2      = ../images/groundpieces/ground/misc/stone2.png     (type=surface, x=0, y=0, width=86,  height=54);

	cabin       = ../images/groundpieces/ground/snow/cabin.png   (type=surface, x=0, y=0, width=129, height=94);
	cabin_winter= ../images/groundpieces/ground/snow/cabin_winter.png (type=surface, x=0, y=0, width=129, height=94);
	dragon      = ../images/groundpieces/ground/misc/dragon.png  (type=surface, x=0, y=0, width=255, height=169);

	pillar1     = ../images/groundpieces/ground/desert/pillar1.png (type=surface, x=0, y=0, width=36, height=161);
	pillar2     = ../images/groundpieces/ground/desert/pillar2.png (type=surface, x=0, y=0, width=37, height=130);
	pillar3     = ../images/groundpieces/ground/desert/pillar3.png (type=surface, x=0, y=0, width=36, height=147);

	well        = ../images/groundpieces/ground/misc/well.png    (type=surface, x=0, y=0, width=32, height=54);
	plate       = ../images/groundpieces/solid/misc/metalplate_horiz.png   (type=surface, x=0, y=0, width=64, height=32);
	plate2      = ../images/groundpieces/solid/misc/metalplate_large.png  (type=surface, x=0, y=0, width=64, height=64);
	plate_small = ../images/groundpieces/solid/misc/metalplate_small.png  (type=surface, x=0, y=0, width=32, height=32);

	section signposts {
		basher = ../images/hotspots/signposts/basher.png (type=surface, x=0, y=0, width=28, height=46);
		blocker = ../images/hotspots/signposts/blocker.png (type=surface, x=0, y=0, width=28, height=46);
		bomber = ../images/hotspots/signposts/bomber.png (type=surface, x=0, y=0, width=28, height=46);
		bridger = ../images/hotspots/signposts/bridger.png (type=surface, x=0, y=0, width=28, height=46);
		climber = ../images/hotspots/signposts/climber.png (type=surface, x=0, y=0, width=28, height=46);
		digger = ../images/hotspots/signposts/digger.png (type=surface, x=0, y=0, width=28, height=46);
		floater = ../images/hotspots/signposts/floater.png (type=surface, x=0, y=0, width=28, height=46);
		miner = ../images/hotspots/signposts/miner.png (type=surface, x=0, y=0, width=28, height=46);
		nogo = ../images/hotspots/signposts/nogo.png (type=surface, x=0, y=0, width=28, height=46);
//		e = ../images/hotspots/signposts/e.png (type=surface, x=0, y=0, width=28, height=46);
//		ne = ../images/hotspots/signposts/ne.png (type=surface, x=0, y=0, width=28, height=46);
//		n = ../images/hotspots/signposts/n.png (type=surface, x=0, y=0, width=28, height=46);
//		nw = ../images/hotspots/signposts/nw.png (type=surface, x=0, y=0, width=28, height=46);
//		w = ../images/hotspots/signposts/w.png (type=surface, x=0, y=0, width=28, height=46);	
//		sw = ../images/hotspots/signposts/sw.png (type=surface, x=0, y=0, width=28, height=46);
//		s = ../images/hotspots/signposts/s.png (type=surface, x=0, y=0, width=28, height=46);
//		se = ../images/hotspots/signposts/se.png (type=surface, x=0, y=0, width=28, height=46);
	}

//	tutorial_blocker = ../images/hotspots/tutorial-blocker.png (type=surface, x=0, y=0, width=28, height=46);
//	tutorial_digger = ../images/hotspots/tutorial_digger.png  (type=surface, x=0, y=0, width=28, height=46);
//	tutorial_bridger  = ../images/hotspots/tutorial_bridger.png  (type=surface, x=0, y=0, width=28, height=46);
//	tutorial_left   = ../images/hotspots/tutorial_left.png  (type=surface, x=0, y=0, width=28, height=46);
//	tutorial_right  = ../images/hotspots/tutorial_right.png  (type=surface, x=0, y=0, width=28, height=46);
}

section GroundPieces
{
       cable1 = ../images/groundpieces/ground/ordina/cable1.png (type=surface);
       cable2 = ../images/groundpieces/ground/ordina/cable2.png (type=surface);
       cdrom1 = ../images/groundpieces/ground/ordina/cdrom1.png (type=surface);
       clavier1 = ../images/groundpieces/ground/ordina/clavier1.png (type=surface);
       cpu1 = ../images/groundpieces/ground/ordina/cpu1.png (type=surface);
       ecran = ../images/groundpieces/ground/ordina/ecran.png (type=surface);
       sol1 = ../images/groundpieces/ground/ordina/sol2.png (type=surface);
       sol2 = ../images/groundpieces/ground/ordina/sol3.png (type=surface);
       sol3 = ../images/groundpieces/ground/ordina/sol4.png (type=surface);

	bpiece1  = ../images/groundpieces/ground/misc/bpiece1.png (type=surface, x=0, y=0, width=217, height=154);
	bpiece2  = ../images/groundpieces/ground/misc/bpiece2.png (type=surface, x=0, y=0, width=166, height=192);
	bpiece3  = ../images/groundpieces/ground/misc/bpiece3.png (type=surface, x=0, y=0, width=220, height=146);
	bpiece4  = ../images/groundpieces/ground/misc/bpiece4.png (type=surface, x=0, y=0, width=156, height=215);
	bpiece5  = ../images/groundpieces/ground/misc/bpiece5.png (type=surface, x=0, y=0, width=226, height=100);
	bpiece6  = ../images/groundpieces/ground/misc/bpiece6.png (type=surface, x=0, y=0, width=161, height=168);

	bpice1  = ../images/groundpieces/ground/misc/bpiece1.png (type=surface, x=0, y=0, width=217, height=154);
	bpice2  = ../images/groundpieces/ground/misc/bpiece2.png (type=surface, x=0, y=0, width=166, height=192);
	bpice3  = ../images/groundpieces/ground/misc/bpiece3.png (type=surface, x=0, y=0, width=220, height=146);
	bpice4  = ../images/groundpieces/ground/misc/bpiece4.png (type=surface, x=0, y=0, width=156, height=215);
	bpice5  = ../images/groundpieces/ground/misc/bpiece5.png (type=surface, x=0, y=0, width=226, height=100);
	bpice6  = ../images/groundpieces/ground/misc/bpiece6.png (type=surface, x=0, y=0, width=161, height=168);

	piece1  = ../images/groundpieces/ground/misc/grey_stone1.png (type=surface, x=0, y=0, width=190, height=109);
	piece2  = ../images/groundpieces/ground/misc/grey_stone2.png (type=surface, x=0, y=0, width=125, height=222);
	piece3  = ../images/groundpieces/ground/misc/grey_stone3.png (type=surface, x=0, y=0, width=140, height=145);
	piece4  = ../images/groundpieces/ground/misc/grey_stone4.png (type=surface, x=0, y=0, width=216, height=68);
	piece5  = ../images/groundpieces/ground/misc/grey_stone5.png (type=surface, x=0, y=0, width=218, height=91);


	pice1  = ../images/groundpieces/ground/misc/grey_stone1.png (type=surface, x=0, y=0, width=190, height=109);
	pice2  = ../images/groundpieces/ground/misc/grey_stone2.png (type=surface, x=0, y=0, width=125, height=222);
	pice3  = ../images/groundpieces/ground/misc/grey_stone3.png (type=surface, x=0, y=0, width=140, height=145);
	pice4  = ../images/groundpieces/ground/misc/grey_stone4.png (type=surface, x=0, y=0, width=216, height=68);
	pice5  = ../images/groundpieces/ground/misc/grey_stone5.png (type=surface, x=0, y=0, width=218, height=91);

	green1 = ../images/groundpieces/ground/misc/green1.png (type=surface, x=0, y=0, width=190, height=73);
	green2 = ../images/groundpieces/ground/misc/green2.png (type=surface, x=0, y=0, width=109, height=128);

	// Begin: Only here for backward compatibility
	pipe_h       = ../images/groundpieces/ground/industrial/pipe1.png     (type=surface, x=0, y=0, width=32,  height=22);
	pipe_v       = ../images/groundpieces/ground/industrial/pipe2.png     (type=surface, x=0, y=0, width=22,  height=32);

	pipe_ph       = ../images/groundpieces/ground/industrial/pipe4.png     (type=surface, x=0, y=0, width=32,  height=22);
	pipe_pv       = ../images/groundpieces/ground/industrial/pipe5.png     (type=surface, x=0, y=0, width=22,  height=32);

	pipe_ul       = ../images/groundpieces/ground/industrial/pipe_ul.png     (type=surface, x=0, y=0, width=32,  height=32);
	pipe_ur       = ../images/groundpieces/ground/industrial/pipe_ur.png     (type=surface, x=0, y=0, width=32,  height=32);
	pipe_dl       = ../images/groundpieces/ground/industrial/pipe_bl.png     (type=surface, x=0, y=0, width=32,  height=32);
	pipe_dr       = ../images/groundpieces/ground/industrial/pipe_br.png     (type=surface, x=0, y=0, width=32,  height=32);
	// End: Only here for backward compatibility

//	tree          = ../images/groundpieces/ground/tree.png     (type=surface, x=0, y=0, width=192,  height=257);

	section Jungle 
	{
		branch1 = ../images/groundpieces/ground/jungle/branch1.png (type=surface, x=0, y=0, width=337,  height=40);
		branch2 = ../images/groundpieces/ground/jungle/branch2.png (type=surface, x=0, y=0, width=326,  height=112);
		branch3 = ../images/groundpieces/ground/jungle/branch3.png (type=surface, x=0, y=0, width=226,  height=269);
		branch4 = ../images/groundpieces/ground/jungle/branch4.png (type=surface, x=0, y=0, width=326,  height=112);
		branch5 = ../images/groundpieces/ground/jungle/branch5.png (type=surface, x=0, y=0, width=226,  height=269);
		branch6 = ../images/groundpieces/ground/jungle/branch6.png (type=surface, x=0, y=0, width=41,  height=337);

		carabatree = ../images/groundpieces/ground/jungle/carabatree.png (type=surface, x=0, y=0, width=325,  height=408);
		carabatreemedium = ../images/groundpieces/ground/jungle/carabatreemedium.png (type=surface, x=0, y=0, width=257,  height=300);
		carabatreesmall = ../images/groundpieces/ground/jungle/carabatreesmall.png (type=surface, x=0, y=0, width=164,  height=190);
		twist = ../images/groundpieces/ground/jungle/twist.png (type=surface, x=0, y=0, width=405,  height=70);
		twistsmooth = ../images/groundpieces/ground/jungle/twistsmooth.png (type=surface, x=0, y=0, width=405,  height=70);

		stick1 = ../images/groundpieces/ground/jungle/stick1.png (type=surface, x=0, y=0, width=446,  height=21);
		stick2 = ../images/groundpieces/ground/jungle/stick2.png (type=surface, x=0, y=0, width=312,  height=21);
		stick3 = ../images/groundpieces/ground/jungle/stick3.png (type=surface, x=0, y=0, width=289,  height=21);
		stick4 = ../images/groundpieces/ground/jungle/stick4.png (type=surface, x=0, y=0, width=232,  height=21);
		stick5 = ../images/groundpieces/ground/jungle/stick5.png (type=surface, x=0, y=0, width=185,  height=21);
	}

	section Crystal 
	{
		block         = ../images/groundpieces/ground/crystal/block.png         (type=surface, x=0, y=0, width=32,  height=32);
		column        = ../images/groundpieces/ground/crystal/column.png        (type=surface, x=0, y=0, width=32,  height=95);
		diagcrystal   = ../images/groundpieces/ground/crystal/diagcrystal.png   (type=surface, x=0, y=0, width=100,  height=138);
		hexcrystal    = ../images/groundpieces/ground/crystal/hexcrystal.png    (type=surface, x=0, y=0, width=175,  height=139);
		horiz         = ../images/groundpieces/ground/crystal/horiz.png         (type=surface, x=0, y=0, width=84,  height=41);
		horiz2        = ../images/groundpieces/ground/crystal/horiz2.png        (type=surface, x=0, y=0, width=87,  height=42);
		pipe          = ../images/groundpieces/ground/crystal/pipe.png	         (type=surface, x=0, y=0, width=101,  height=32);
		pipeend       = ../images/groundpieces/ground/crystal/pipeend.png       (type=surface, x=0, y=0, width=41,  height=41);
		platform      = ../images/groundpieces/ground/crystal/platform.png      (type=surface, x=0, y=0, width=126,  height=74);
		platform2     = ../images/groundpieces/ground/crystal/platform2.png     (type=surface, x=0, y=0, width=81,  height=80);
		pointleft     = ../images/groundpieces/ground/crystal/pointleft.png     (type=surface, x=0, y=0, width=32,  height=32);
		pointright    = ../images/groundpieces/ground/crystal/pointright.png    (type=surface, x=0, y=0, width=32,  height=32);
		skinnycrystal = ../images/groundpieces/ground/crystal/skinnycrystal.png (type=surface, x=0, y=0, width=65,  height=208);
		smcrystal     = ../images/groundpieces/ground/crystal/smcrystal.png     (type=surface, x=0, y=0, width=100,  height=50);
		tesselate     = ../images/groundpieces/ground/crystal/tesselate.png     (type=surface, x=0, y=0, width=64,  height=64);
	}

	section Snow 
	{
		snowman   = ../images/groundpieces/ground/snow/snowman.png  (type=surface, x=0, y=0, width=67,  height=110);
		xmas-tree = ../images/groundpieces/ground/snow/xmas-tree.png (type=surface, x=0, y=0, width=74,  height=98);
		piece1    = ../images/groundpieces/ground/snow/piece1.png  (type=surface, x=0, y=0, width=126,  height=73);
		slopedr = ../images/groundpieces/ground/snow/slopedr.png (type=surface, x=0, y=0, width=126, height=73);

		gpiece1    = ../images/groundpieces/ground/snow/gpiece1.png  (type=surface);

		piece2    = ../images/groundpieces/ground/snow/piece2.png  (type=surface, x=0, y=0, width=80,  height=19);
		piece3    = ../images/groundpieces/ground/snow/piece3.png  (type=surface, x=0, y=0, width=178,  height=22);

		ice1      = ../images/groundpieces/ground/snow/ice1.png    (type=surface, x=0, y=0, width=54,  height=65);
		ice2      = ../images/groundpieces/ground/snow/ice2.png    (type=surface, x=0, y=0, width=74,  height=41);

		igloo     = ../images/groundpieces/ground/snow/igloo.png    (type=surface, x=0, y=0, width=93,  height=54);

//		gpiece1   = ../images/groundpieces/ground/snow/grey_stone1.png    (type=surface, x=0, y=0, width=93,  height=218);
	}

	section Rock 
	{
	        real1 = ../images/groundpieces/ground/real/real1.png (type=surface, x=0, y=0, width=222, height=134);
	        real2 = ../images/groundpieces/ground/real/real2.png (type=surface, x=0, y=0, width=351, height=163);
	        real3 = ../images/groundpieces/ground/real/real3.png (type=surface, x=0, y=0, width=263, height=156);
	        real4 = ../images/groundpieces/ground/real/real4.png (type=surface, x=0, y=0, width=200, height=93);
	        real5 = ../images/groundpieces/ground/real/real5.png (type=surface, x=0, y=0, width=220, height=49);
	        real6 = ../images/groundpieces/ground/real/real6.png (type=surface, x=0, y=0, width=110, height=206);
	        real7 = ../images/groundpieces/ground/real/real7.png (type=surface, x=0, y=0, width=247, height=132);
	        real8 = ../images/groundpieces/ground/real/real8.png (type=surface, x=0, y=0, width=263, height=132);
	        real9 = ../images/groundpieces/ground/real/real9.png (type=surface, x=0, y=0, width=79, height=76);

	        real10 = ../images/groundpieces/ground/real/real10.png (type=surface, x=0, y=0, width=100, height=95);
	        real11 = ../images/groundpieces/ground/real/real11.png (type=surface, x=0, y=0, width=149, height=91);
	        real12 = ../images/groundpieces/ground/real/real12.png (type=surface, x=0, y=0, width=180, height=79);
	        real13 = ../images/groundpieces/ground/real/real13.png (type=surface, x=0, y=0, width=300, height=56);
	        real14 = ../images/groundpieces/ground/real/real14.png (type=surface, x=0, y=0, width=100, height=110);
	        real15 = ../images/groundpieces/ground/real/real15.png (type=surface, x=0, y=0, width=150, height=114);
	        real16 = ../images/groundpieces/ground/real/real16.png (type=surface, x=0, y=0, width=150, height=94);
	        real17 = ../images/groundpieces/ground/real/real17.png (type=surface, x=0, y=0, width=175, height=94);
	        real18 = ../images/groundpieces/ground/real/real18.png (type=surface, x=0, y=0, width=160, height=106);
        	real19 = ../images/groundpieces/ground/real/real19.png (type=surface, x=0, y=0, width=184, height=79);
	        real20 = ../images/groundpieces/ground/real/real20.png (type=surface, x=0, y=0, width=181, height=82);
	        real21 = ../images/groundpieces/ground/real/real21.png (type=surface, x=0, y=0, width=185, height=106);
	        real22 = ../images/groundpieces/ground/real/real22.png (type=surface, x=0, y=0, width=160, height=142);
        	real23 = ../images/groundpieces/ground/real/real23.png (type=surface, x=0, y=0, width=200, height=81);
	        real24 = ../images/groundpieces/ground/real/real24.png (type=surface, x=0, y=0, width=138, height=84);
	        real25 = ../images/groundpieces/ground/real/real25.png (type=surface, x=0, y=0, width=184, height=128);
	        real26 = ../images/groundpieces/ground/real/real26.png (type=surface, x=0, y=0, width=154, height=95);
	        real27 = ../images/groundpieces/ground/real/real27.png (type=surface, x=0, y=0, width=180, height=133);
	        real28 = ../images/groundpieces/ground/real/real28.png (type=surface, x=0, y=0, width=140, height=155);
	        real29 = ../images/groundpieces/ground/real/real29.png (type=surface, x=0, y=0, width=150, height=89);
	        real30 = ../images/groundpieces/ground/real/real30.png (type=surface, x=0, y=0, width=171, height=101);
	        real31 = ../images/groundpieces/ground/rock/blackrock1.png (type=surface, x=0, y=0, width=136, height=171);
	        real32 = ../images/groundpieces/ground/rock/blackrock2.png (type=surface, x=0, y=0, width=150, height=171);
	        real33 = ../images/groundpieces/ground/rock/blackrock3.png (type=surface, x=0, y=0, width=170, height=175);
	        real34 = ../images/groundpieces/ground/rock/blackrock4.png (type=surface, x=0, y=0, width=156, height=150);
	        real35 = ../images/groundpieces/ground/real/real35.png (type=surface, x=0, y=0, width=179, height=48);
        	real36 = ../images/groundpieces/ground/real/real36.png (type=surface, x=0, y=0, width=181, height=60);
	       	real37 = ../images/groundpieces/ground/real/real37.png (type=surface, x=0, y=0, width=184, height=64);

	        fish = ../images/groundpieces/ground/real/fish.png (type=surface, x=0, y=0, width=166, height=50);
	        green1 = ../images/groundpieces/ground/real/green1.png (type=surface, x=0, y=0, width=122, height=374);
	        green2 = ../images/groundpieces/ground/real/green2.png (type=surface, x=0, y=0, width=207, height=191);

	        granit1 = ../images/groundpieces/solid/rock/granit1.png (type=surface, x=0, y=0, width=173, height=107);
	        granit2 = ../images/groundpieces/solid/rock/granit2.png (type=surface, x=0, y=0, width=139, height=112);
	        granit3 = ../images/groundpieces/solid/rock/granit3.png (type=surface, x=0, y=0, width=126, height=182);
	        granit4 = ../images/groundpieces/solid/rock/granit4.png (type=surface, x=0, y=0, width=127, height=87);
	        granit5 = ../images/groundpieces/solid/rock/granit5.png (type=surface, x=0, y=0, width=138, height=101);
	        granit6 = ../images/groundpieces/solid/rock/granit6.png (type=surface, x=0, y=0, width=88, height=150);

		rock1   = ../images/groundpieces/ground/foliage/rock1.png    (type=surface, x=0, y=0, width=188,  height=98);
		rock2   = ../images/groundpieces/ground/foliage/rock2.png    (type=surface, x=0, y=0, width=152,  height=196);
		rock3   = ../images/groundpieces/ground/foliage/rock3.png    (type=surface, x=0, y=0, width=210,  height=121);
		rock4   = ../images/groundpieces/ground/foliage/rock4.png    (type=surface, x=0, y=0, width=136,  height=191);
		rock5   = ../images/groundpieces/ground/foliage/rock5.png    (type=surface, x=0, y=0, width=101,  height=88);
		rock6   = ../images/groundpieces/ground/foliage/rock6.png    (type=surface, x=0, y=0, width=143,  height=185);
	}

	section Test
	{
		test1 = ../images/groundpieces/ground/test/test1.png     (type=surface, x=0, y=0, width=105,  height=49);
		test2 = ../images/groundpieces/ground/test/test2.png     (type=surface, x=0, y=0, width=71,  height=38);
		test3 = ../images/groundpieces/ground/test/test3.png     (type=surface, x=0, y=0, width=104,  height=59);
		test4 = ../images/groundpieces/ground/test/test4.png     (type=surface, x=0, y=0, width=107,  height=45);
		test5 = ../images/groundpieces/ground/test/test5.png     (type=surface, x=0, y=0, width=47,  height=42);
	        test6 = ../images/groundpieces/ground/test/test6.png     (type=surface, x=0, y=0, width=13, height=64);
	}

	section Foliage 
	{
		flax1     = ../images/groundpieces/ground/foliage/flax1.png     (type=surface, x=0, y=0, width=100,  height=91);
		flax2     = ../images/groundpieces/ground/foliage/flax2.png     (type=surface, x=0, y=0, width=92,  height=84);
		midtree1  = ../images/groundpieces/ground/foliage/midtree1.png  (type=surface, x=0, y=0, width=114,  height=161);
		midtree2  = ../images/groundpieces/ground/foliage/midtree2.png  (type=surface, x=0, y=0, width=169,  height=189);
		oak1      = ../images/groundpieces/ground/foliage/oak1.png      (type=surface, x=0, y=0, width=168,  height=215);
		smallbush = ../images/groundpieces/ground/foliage/smallbush.png (type=surface, x=0, y=0, width=57,  height=24);
		smallbush2 = ../images/groundpieces/ground/foliage/smallbush2.png (type=surface, x=0, y=0, width=107,  height=88);
		smallbush3 = ../images/groundpieces/ground/foliage/smallbush3.png (type=surface, x=0, y=0, width=95,  height=37);
		talltree  = ../images/groundpieces/ground/foliage/talltree.png  (type=surface, x=0, y=0, width=84,  height=200);

		turfconcave      =  ../images/groundpieces/transparent/foliage/turfconcave.png     (type=surface, x=0, y=0, width=59,  height=16);
		turfflat         =  ../images/groundpieces/transparent/foliage/turfflat.png        (type=surface, x=0, y=0, width=90,  height=13);
		turfslopeleft    =  ../images/groundpieces/transparent/foliage/turfslopeleft.png   (type=surface, x=0, y=0, width=70,  height=17);
		turfslopeleft20  =  ../images/groundpieces/transparent/foliage/turfslopeleft20.png (type=surface, x=0, y=0, width=52,  height=20);
		turfsloperight   =  ../images/groundpieces/transparent/foliage/turfsloperight.png  (type=surface, x=0, y=0, width=40,  height=17);
		turfsloperight20 = ../images/groundpieces/transparent/foliage/turfsloperight20.png (type=surface, x=0, y=0, width=52,  height=20);
		turfsmallbump    =  ../images/groundpieces/transparent/foliage/turfsmallbump.png   (type=surface, x=0, y=0, width=20,  height=16);
		turfsmallnook    =  ../images/groundpieces/transparent/foliage/turfsmallnook.png   (type=surface, x=0, y=0, width=20,  height=17);
		turftufty        = ../images/groundpieces/transparent/foliage/turftufty.png        (type=surface, x=0, y=0, width=20,  height=17);
	}

	section Mushroom
	{
        	toadstool1 = ../images/groundpieces/ground/mushroom/toadstool1.png (type=surface, x=0, y=0, width=211, height=187);
	        toadstool2 = ../images/groundpieces/ground/mushroom/toadstool2.png (type=surface, x=0, y=0, width=63, height=56);
	        toadstool3 = ../images/groundpieces/ground/mushroom/toadstool3.png (type=surface, x=0, y=0, width=214, height=178);
	        toadstool4 = ../images/groundpieces/ground/mushroom/toadstool4.png (type=surface, x=0, y=0, width=197, height=210);
	}

	section Sweets
	{
		allsort1          = ../images/groundpieces/ground/sweets/allsort1.png (type=surface, x=0, y=0, width=61,  height=60);
		allsort2          = ../images/groundpieces/ground/sweets/allsort2.png (type=surface, x=0, y=0, width=67,  height=52);
		aquachoc = ../images/groundpieces/ground/sweets/aquachoc.png (type=surface, x=0, y=0, width=189, height=71);
		canestalk = ../images/groundpieces/ground/sweets/canestalk.png (type=surface, x=0, y=0, width=15, height=61);
		canestub = ../images/groundpieces/ground/sweets/canestub.png (type=surface, x=0, y=0, width=16, height=7);
		canetop = ../images/groundpieces/ground/sweets/canetop.png (type=surface, x=0, y=0, width=115, height=72);
		chocbar = ../images/groundpieces/ground/sweets/chocbar.png (type=surface, x=0, y=0, width=300, height=74);
		chocbloc = ../images/groundpieces/ground/sweets/chocbloc.png (type=surface, x=0, y=0, width=64, height=64);
		chocbloc-lowleft = ../images/groundpieces/ground/sweets/chocbloc-lowleft.png (type=surface, x=0, y=0, width=64, height=64);
		chocbloc-lowright = ../images/groundpieces/ground/sweets/chocbloc-lowright.png (type=surface, x=0, y=0, width=64, height=64);
		chocbloc-topleft = ../images/groundpieces/ground/sweets/chocbloc-topleft.png (type=surface, x=0, y=0, width=64, height=64);
		chocbloc-topright = ../images/groundpieces/ground/sweets/chocbloc-topright.png (type=surface, x=0, y=0, width=64, height=64);
		goldchoc = ../images/groundpieces/ground/sweets/goldchoc.png (type=surface, x=0, y=0, width=190, height=71);
		greenjellybaby = ../images/groundpieces/ground/sweets/greenjellybaby.png (type=surface, x=0, y=0, width=64, height=84);
		icecream          = ../images/groundpieces/ground/sweets/icecream.png (type=surface, x=0, y=0, width=114,  height=273);
		icecream = ../images/groundpieces/ground/sweets/icecream.png (type=surface, x=0, y=0, width=114, height=273);
		jellybaby = ../images/groundpieces/ground/sweets/greenjellybaby.png (type=surface);
		jellybaby2 = ../images/groundpieces/ground/sweets/redjellybaby.png (type=surface);
		jube1 = ../images/groundpieces/ground/sweets/jube1.png (type=surface, x=0, y=0, width=49, height=42);
		jube2 = ../images/groundpieces/ground/sweets/jube2.png (type=surface, x=0, y=0, width=45, height=48);
		lollipop = ../images/groundpieces/ground/sweets/lollipop.png (type=surface, x=0, y=0, width=42, height=238);
		lollipop = ../images/groundpieces/ground/sweets/lollipop.png (type=surface, x=0, y=0, width=42, height=238);
		lollipop-purp = ../images/groundpieces/ground/sweets/lollipop-purp.png (type=surface, x=0, y=0, width=42, height=238);
		lollipop-purp = ../images/groundpieces/ground/sweets/lollipop-purp.png (type=surface, x=0, y=0, width=42, height=238);
		mmblue = ../images/groundpieces/ground/sweets/mmblue.png (type=surface, x=0, y=0, width=50, height=41);
		mmbrown = ../images/groundpieces/ground/sweets/mmbrown.png (type=surface, x=0, y=0, width=50, height=41);
		mmred = ../images/groundpieces/ground/sweets/mmred.png (type=surface, x=0, y=0, width=54, height=31);
		mmyellow = ../images/groundpieces/ground/sweets/mmyellow.png (type=surface, x=0, y=0, width=40, height=51);
		purpchoc = ../images/groundpieces/ground/sweets/purpchoc.png (type=surface, x=0, y=0, width=189, height=70);
		purplechoc = ../images/groundpieces/ground/sweets/purplechoc.png (type=surface, x=0, y=0, width=150, height=57);
		redjellybaby = ../images/groundpieces/ground/sweets/redjellybaby.png (type=surface, x=0, y=0, width=85, height=63);
//		jube1 = ../images/groundpieces/ground/sweets/jube1.png (type=surface, x=0, y=0, width=49, height=42);
	        cake1 = ../images/groundpieces/ground/sweets/cake1.png (type=surface, x=0, y=0, width=163, height=101);
	        cake2 = ../images/groundpieces/ground/sweets/cake2.png (type=surface, x=0, y=0, width=121, height=142);
	        cake3 = ../images/groundpieces/ground/sweets/cake3.png (type=surface, x=0, y=0, width=153, height=97);
	        cake4 = ../images/groundpieces/ground/sweets/cake4.png (type=surface, x=0, y=0, width=148, height=98);
	        cake5 = ../images/groundpieces/ground/sweets/cake5.png (type=surface, x=0, y=0, width=152, height=165);
	}

	section Misc
	{
	        bridge_left = ../images/groundpieces/bridge/misc/bridge_left.png (type=surface, x=0, y=0, width=72, height=30);
	        bridge_right = ../images/groundpieces/bridge/misc/bridge_right.png (type=surface, x=0, y=0, width=72, height=30);

		// Begin: Only here for backward compatibility
		bigprickpiece    = ../images/groundpieces/ground/desert/bigprickpiece.png (type=surface, x=0, y=0, width=300,  height=150); 
		middleprickpiece = ../images/groundpieces/ground/desert/middleprickpiece.png (type=surface, x=0, y=0, width=150,  height=75); 
		smallbrickpiece = ../images/groundpieces/ground/desert/smallbrickpiece.png (type=surface, x=0, y=0, width=76,  height=38); 
		// End: Only here for backward compatibility

//		black     = ../images/groundpieces/ground/misc/black.png    (type=surface, x=0, y=0, width=256, height=228);

                bridge1     = ../images/groundpieces/ground/misc/bridge1.png    (type=surface, x=0, y=0, width=216, height=42);

		way         = ../images/groundpieces/ground/desert/platform.png        (type=surface, x=0, y=0, width=117, height=35);
		way_left    = ../images/groundpieces/ground/desert/platform_left.png        (type=surface, x=0, y=0, width=81, height=28);
		way_right   = ../images/groundpieces/ground/desert/platform_right.png        (type=surface, x=0, y=0, width=62, height=26);

		spike     = ../images/groundpieces/ground/misc/spike.png    (type=surface, x=0, y=0, width=262,  height=125);

		piece1 = ../images/groundpieces/ground/misc/piece1.png  (type=surface);
		piece2 = ../images/groundpieces/ground/misc/piece2.png  (type=surface);
		piece3 = ../images/groundpieces/ground/snow/piece3.png  (type=surface);
//		piece4 = ../images/groundpieces/ground/misc/piece4.png  (type=surface);
//		piece5 = ../images/groundpieces/ground/misc/piece5.png  (type=surface);
//		piece6 = ../images/groundpieces/ground/misc/piece6.png  (type=surface);
		piece7 = ../images/groundpieces/ground/snow/piece7.png  (type=surface);
		piece8 = ../images/groundpieces/ground/snow/piece8.png  (type=surface);
		piece9 = ../images/groundpieces/ground/snow/piece9.png  (type=surface);
		
		brick1 = ../images/groundpieces/ground/misc/brick1.png  (type=surface);
		brick2 = ../images/groundpieces/ground/misc/brick2.png  (type=surface);
		brick3 = ../images/groundpieces/ground/misc/brick3.png  (type=surface);
		brick4 = ../images/groundpieces/ground/misc/brick4.png  (type=surface);

		stoneblock1 = ../images/groundpieces/ground/misc/stoneblock1.png  (type=surface, x=0, y=0, width=64,  height=64); 
		stoneblock2 = ../images/groundpieces/ground/misc/stoneblock2.png  (type=surface, x=0, y=0, width=64,  height=64); 
		stoneblock3 = ../images/groundpieces/ground/misc/stoneblock3.png  (type=surface, x=0, y=0, width=64,  height=64); 

		stoneblock5 = ../images/groundpieces/ground/misc/stoneblock5.png (type=surface, x=0, y=0, width=64, height=64);
		stoneblock6 = ../images/groundpieces/ground/misc/stoneblock6.png (type=surface, x=0, y=0, width=64, height=64);

		pfosten = ../images/groundpieces/ground/misc/pfosten.png  (type=surface, x=0, y=0, width=163,  height=201); 
	}
	
	section Space
	{
		grid1 = ../images/groundpieces/bridge/space/grid1.png  (type=surface, x=0, y=0, width=51,  height=51); 
		grid2 = ../images/groundpieces/bridge/space/grid2.png  (type=surface, x=0, y=0, width=51,  height=51); 
		grid3 = ../images/groundpieces/bridge/space/grid3.png  (type=surface, x=0, y=0, width=41,  height=41); 

	        smallblock1 = ../images/groundpieces/ground/space/smallblock1.png (type=surface, x=0, y=0, width=32, height=32);
	        smallblock2 = ../images/groundpieces/ground/space/smallblock2.png (type=surface, x=0, y=0, width=32, height=32);
	        smallblock3 = ../images/groundpieces/ground/space/smallblock3.png (type=surface, x=0, y=0, width=32, height=32);

	        diagonal = ../images/groundpieces/ground/space/diagonal.png (type=surface, x=0, y=0, width=125, height=68);
	        diagonalr = ../images/groundpieces/ground/space/diagonalr.png (type=surface, x=0, y=0, width=125, height=68);
		block1 = ../images/groundpieces/ground/space/block1.png  (type=surface, x=0, y=0, width=201,  height=61);
		tower1 = ../images/groundpieces/ground/space/tower1.png  (type=surface, x=0, y=0, width=64,  height=166); 
		smalltower = ../images/groundpieces/ground/space/smalltower.png  (type=surface, x=0, y=0, width=6,  height=200); 
		way        = ../images/groundpieces/ground/space/way.png  (type=surface, x=0, y=0, width=100,  height=6); 
	}

	section Desert
	{
	        Sluggydu = ../images/groundpieces/ground/desert/sluggydu.png (type=surface, x=0, y=0, width=192, height=36);
	        blobdune = ../images/groundpieces/ground/desert/blobdune.png (type=surface, x=0, y=0, width=112, height=32);

		domedune = ../images/groundpieces/ground/desert/domedune.png (type=surface, x=0, y=0, width=154, height=45);
		flatdune = ../images/groundpieces/ground/desert/flatdune.png (type=surface, x=0, y=0, width=166, height=30);
		peakdune = ../images/groundpieces/ground/desert/peakdune.png (type=surface, x=0, y=0, width=136, height=57);

		bigblock-broken1 = ../images/groundpieces/ground/desert/bigblock-broken1.png (type=surface, x=0, y=0, width=300, height=150);
		bigblock-broken2 = ../images/groundpieces/ground/desert/bigblock-broken2.png (type=surface, x=0, y=0, width=254, height=144);
		bigdune1 = ../images/groundpieces/ground/desert/bigdune1.png (type=surface, x=0, y=0, width=318, height=107);

		bigprickpiece    = ../images/groundpieces/ground/desert/bigprickpiece.png (type=surface, x=0, y=0, width=300,  height=150); 
		middleprickpiece = ../images/groundpieces/ground/desert/middleprickpiece.png (type=surface, x=0, y=0, width=150,  height=75); 
		smallbrickpiece = ../images/groundpieces/ground/desert/smallbrickpiece.png (type=surface, x=0, y=0, width=76,  height=38); 

		pillar1     = ../images/groundpieces/ground/desert/pillar1.png (type=surface, x=0, y=0, width=36, height=161);
		pillar2     = ../images/groundpieces/ground/desert/pillar2.png (type=surface, x=0, y=0, width=37, height=130);
		pillar3     = ../images/groundpieces/ground/desert/pillar3.png (type=surface, x=0, y=0, width=36, height=147);
	}

	section Sortie
	{
	        column1   = ../images/groundpieces/ground/sortie/column.png (type=surface, x=0, y=0, width=168, height=312);
	        column2   = ../images/groundpieces/ground/sortie/column2.png (type=surface, x=0, y=0, width=168, height=312);
	        column3   = ../images/groundpieces/ground/sortie/column3.png (type=surface, x=0, y=0, width=168, height=312);

	        plate_forme       = ../images/groundpieces/ground/sortie/plate_forme.png (type=surface, x=0, y=0, width=267, height=94);
	        plate_forme_left  = ../images/groundpieces/ground/sortie/plate_forme_left.png (type=surface, x=0, y=0, width=243, height=94);
	        plate_forme_right = ../images/groundpieces/ground/sortie/plate_forme_right.png (type=surface, x=0, y=0, width=138, height=84);
	}
}

section Industrial
{
	section Gunge
	{
		gunge1 = ../images/groundpieces/transparent/industrial/gunge1.png (type=surface, x=0, y=0, width=65, height=59);
		gunge10 = ../images/groundpieces/transparent/industrial/gunge10.png (type=surface, x=0, y=0, width=147, height=45);
		gunge11 = ../images/groundpieces/transparent/industrial/gunge11.png (type=surface, x=0, y=0, width=69, height=51);
		gunge12 = ../images/groundpieces/transparent/industrial/gunge12.png (type=surface, x=0, y=0, width=32, height=4);
		gunge13 = ../images/groundpieces/transparent/industrial/gunge13.png (type=surface, x=0, y=0, width=75, height=4);
		gunge14 = ../images/groundpieces/transparent/industrial/gunge14.png (type=surface, x=0, y=0, width=129, height=10);
		gunge15 = ../images/groundpieces/transparent/industrial/gunge15.png (type=surface, x=0, y=0, width=98, height=10);
		gunge2 = ../images/groundpieces/transparent/industrial/gunge2.png (type=surface, x=0, y=0, width=13, height=17);
		gunge3 = ../images/groundpieces/transparent/industrial/gunge3.png (type=surface, x=0, y=0, width=18, height=10);
		gunge4 = ../images/groundpieces/transparent/industrial/gunge4.png (type=surface, x=0, y=0, width=38, height=16);
		gunge5 = ../images/groundpieces/transparent/industrial/gunge5.png (type=surface, x=0, y=0, width=9, height=26);
		gunge6 = ../images/groundpieces/transparent/industrial/gunge6.png (type=surface, x=0, y=0, width=14, height=27);
		gunge7 = ../images/groundpieces/transparent/industrial/gunge7.png (type=surface, x=0, y=0, width=11, height=24);
		gunge8 = ../images/groundpieces/transparent/industrial/gunge8.png (type=surface, x=0, y=0, width=89, height=33);
		gunge9 = ../images/groundpieces/transparent/industrial/gunge9.png (type=surface, x=0, y=0, width=24, height=9);
	}

	steel1 = ../images/groundpieces/solid/industrial/steel1.png (type=surface, x=0, y=0, width=84, height=43);
	steel2 = ../images/groundpieces/solid/industrial/steel2.png (type=surface, x=0, y=0, width=44, height=167);
	steel3 = ../images/groundpieces/solid/industrial/steel3.png (type=surface, x=0, y=0, width=167, height=44);
	steel4 = ../images/groundpieces/solid/industrial/steel4.png (type=surface, x=0, y=0, width=233, height=105);
	steel5 = ../images/groundpieces/solid/industrial/steel5.png (type=surface, x=0, y=0, width=253, height=106);

	greybri1 = ../images/groundpieces/solid/industrial/greybri1.png (type=surface, x=0, y=0, width=198, height=62);
	greybri2 = ../images/groundpieces/solid/industrial/greybri2.png (type=surface, x=0, y=0, width=198, height=62);
	greybri3 = ../images/groundpieces/solid/industrial/greybri3.png (type=surface, x=0, y=0, width=198, height=62);
	greybri5 = ../images/groundpieces/solid/industrial/greybri5.png (type=surface, x=0, y=0, width=99, height=62);

	screw = ../images/groundpieces/solid/industrial/screw.png (type=surface, x=0, y=0, width=23, height=23);
	rivet = ../images/groundpieces/solid/industrial/rivet.png (type=surface, x=0, y=0, width=23, height=23);

	pipe_h       = ../images/groundpieces/ground/industrial/pipe1.png     (type=surface, x=0, y=0, width=32,  height=22);
	pipe_v       = ../images/groundpieces/ground/industrial/pipe2.png     (type=surface, x=0, y=0, width=22,  height=32);

	pipe_ph       = ../images/groundpieces/ground/industrial/pipe4.png     (type=surface, x=0, y=0, width=32,  height=22);
	pipe_pv       = ../images/groundpieces/ground/industrial/pipe5.png     (type=surface, x=0, y=0, width=22,  height=32);

	pipe_ul       = ../images/groundpieces/ground/industrial/pipe_ul.png     (type=surface, x=0, y=0, width=32,  height=32);
	pipe_ur       = ../images/groundpieces/ground/industrial/pipe_ur.png     (type=surface, x=0, y=0, width=32,  height=32);
	pipe_dl       = ../images/groundpieces/ground/industrial/pipe_bl.png     (type=surface, x=0, y=0, width=32,  height=32);
	pipe_dr       = ../images/groundpieces/ground/industrial/pipe_br.png     (type=surface, x=0, y=0, width=32,  height=32);

	weld1  = ../images/groundpieces/solid/industrial/weld1.png (type=surface, x=0, y=0, width=9, height=21);
        weld2  = ../images/groundpieces/solid/industrial/weld2.png (type=surface, x=0, y=0, width=8, height=26);
        weld3  = ../images/groundpieces/solid/industrial/weld3.png (type=surface, x=0, y=0, width=7, height=55);
        weld4  = ../images/groundpieces/solid/industrial/weld4.png (type=surface, x=0, y=0, width=17, height=9);
        weld5  = ../images/groundpieces/solid/industrial/weld5.png (type=surface, x=0, y=0, width=9, height=30);
        weld6  = ../images/groundpieces/solid/industrial/weld6.png (type=surface, x=0, y=0, width=101, height=10);
        weld7  = ../images/groundpieces/solid/industrial/weld7.png (type=surface, x=0, y=0, width=53, height=7);
        joint1 = ../images/groundpieces/solid/industrial/joint1.png (type=surface, x=0, y=0, width=60, height=54);
        joint2 = ../images/groundpieces/solid/industrial/joint2.png (type=surface, x=0, y=0, width=44, height=53);
        joint3 = ../images/groundpieces/solid/industrial/joint3.png (type=surface, x=0, y=0, width=55, height=44);
        joint4 = ../images/groundpieces/solid/industrial/joint4.png (type=surface, x=0, y=0, width=53, height=50);
        joint5 = ../images/groundpieces/solid/industrial/joint5.png (type=surface, x=0, y=0, width=57, height=56);
}

section Grass
{
	grass1 = ../images/groundpieces/transparent/foliage/grass1.png (type=surface, x=0, y=0, width=31, height=21);
	grass10 = ../images/groundpieces/transparent/foliage/grass10.png (type=surface, x=0, y=0, width=14, height=7);
	grass11 = ../images/groundpieces/transparent/foliage/grass11.png (type=surface, x=0, y=0, width=14, height=10);
	grass12 = ../images/groundpieces/transparent/foliage/grass12.png (type=surface, x=0, y=0, width=12, height=10);
	grass13 = ../images/groundpieces/transparent/foliage/grass13.png (type=surface, x=0, y=0, width=10, height=6);
	grass14 = ../images/groundpieces/transparent/foliage/grass14.png (type=surface, x=0, y=0, width=13, height=6);
	grass15 = ../images/groundpieces/transparent/foliage/grass15.png (type=surface, x=0, y=0, width=54, height=9);
	grass16 = ../images/groundpieces/transparent/foliage/grass16.png (type=surface, x=0, y=0, width=11, height=7);
	grass2 = ../images/groundpieces/transparent/foliage/grass2.png (type=surface, x=0, y=0, width=14, height=21);
	grass3 = ../images/groundpieces/transparent/foliage/grass3.png (type=surface, x=0, y=0, width=12, height=11);
	grass4 = ../images/groundpieces/transparent/foliage/grass4.png (type=surface, x=0, y=0, width=13, height=21);
	grass5 = ../images/groundpieces/transparent/foliage/grass5.png (type=surface, x=0, y=0, width=9, height=5);
	grass6 = ../images/groundpieces/transparent/foliage/grass6.png (type=surface, x=0, y=0, width=37, height=23);
	grass7 = ../images/groundpieces/transparent/foliage/grass7.png (type=surface, x=0, y=0, width=12, height=9);
	grass8 = ../images/groundpieces/transparent/foliage/grass8.png (type=surface, x=0, y=0, width=13, height=7);
	grass9 = ../images/groundpieces/transparent/foliage/grass9.png (type=surface, x=0, y=0, width=14, height=8);
}

section Themes
{
//	crystal = ../images/themes/crystal.png   (type=surface, x=0, y=0, width=256,  height=128);
//	xmas99  = ../images/themes/xmas99.png    (type=surface, x=0, y=0, width=240,  height=98);
//	tutorial = ../images/themes/tutorial.png    (type=surface, x=0, y=0, width=236,  height=154);
//	space = ../images/themes/space.png    (type=surface, x=0, y=0, width=258,  height=145);
}



section Exits {
        desert_tut = ../images/exits/desert_tut.png (type=surface, x=0, y=0, width=122, height=115);
	ice        = ../images/exits/ice.png       (type=surface, x=0, y=0, width=64, height=64);
	stone      = ../images/exits/stone.png     (type=surface, x=0, y=0, width=75, height=60);
	desert     = ../images/exits/desertexit.png  (type=surface, x=0, y=0, width=150, height=100);
	space      = ../images/exits/space.png  (type=surface, x=0, y=0, width=154, height=118, array=4x1);
	sweetexit  = ../images/exits/sweetexit.png (type=surface, x=0, y=0, width=180,  height=121);
	sortie  = ../images/exits/sortie.png (type=surface, x=0, y=0, width=78,  height=78);
	sortie_1  = ../images/exits/ordina.png (type=surface);
	sortie_anim  = ../images/exits/sortie_anim.png (type=surface, x=0, y=0, width=78,  height=90, array=11x1);
	industrial = ../images/exits/industrial.png (type=surface, x=0, y=0, width=145, height=118);
        crystal = ../images/exits/crystal.png (type=surface, x=0, y=0, width=154, height=105);
}

section Entrances {
//	gate        = ../images/entrances/gate.png      (type=surface, x=0, y=0, width=150, height=150, array=6x1);
	entrance    = ../images/entrances/entrance.png   (type=surface, x=0, y=0, width=64, height=32);

	generic =  ../images/entrances/generic.png (type=surface, x=0, y=0, width=64, height=64);

	woodthing_nmov = ../images/entrances/woodthing_nmov.png   (type=surface, x=0, y=0, width=151, height=157);
	woodthing_mov  = ../images/entrances/woodthing_mov.png   (type=surface, x=0, y=0, width=105, height=17, array=17x1);

	cloud  = ../images/entrances/cloudent.png   (type=surface, x=0, y=0, width=188, height=90);
	space = ../images/entrances/space.png (type=surface, x=0, y=0, width=62, height=64, array=5x1);

//	sun = ../images/hotspots/sun.png (type=surface, x=0, y=0, width=215, height=215);

        wood_bottom = ../images/entrances/wood_bottom.png (type=surface, x=0, y=0, width=100, height=57);
        wood_top = ../images/entrances/wood_top.png (type=surface, x=0, y=0, width=100, height=44);

	entrance_eyes = ../images/entrances/eyes_entrance.png (type=surface, x=0, y=0, width=273, height=221);
	entrance_eyes_anim = ../images/entrances/eyes_entrance_anim.png (type=surface, x=0, y=0, width=166, height=72, array=7x1);
	underlay_eyes = ../images/entrances/eyes_underlay.png (type=surface, x=0, y=0, width=166, height=72);

        industrial = ../images/entrances/industrial.png (type=surface, x=0, y=0, width=105, height=158);
        industrial_top = ../images/entrances/industrial_top.png (type=surface, x=0, y=0, width=43, height=53);

//        industrial = ../images/entrances/industrial.png (type=surface, x=0, y=0, width=129, height=193);
//        industrial_top = ../images/entrances/industrial_top.png (type=surface, x=0, y=0, width=52, height=65);
}


section Ywlam
{
	bigsand = ../images/groundpieces/ground/desert/bigsand.png (type=surface, x=0, y=0, width=300,  height=150); 
	mediumsand = ../images/groundpieces/ground/desert/mediumsand.png (type=surface, x=0, y=0, width=150,  height=75); 
	smallsand = ../images/groundpieces/ground/desert/smallsand.png (type=surface, x=0, y=0, width=75,  height=37); 
	rightsmallsand = ../images/groundpieces/ground/desert/rightsmallsand.png (type=surface, x=0, y=0, width=75,  height=37); 
	leftsmallsand = ../images/groundpieces/ground/desert/leftsmallsand.png (type=surface, x=0, y=0, width=75,  height=37); 
//	bigblack = ../images/groundpieces/ground/desert/bigblack.png (type=surface, x=0, y=0, width=300,  height=150); 
//	mediumblack = ../images/groundpieces/ground/desert/mediumblack.png (type=surface, x=0, y=0, width=150,  height=75); 
//	smallblack = ../images/groundpieces/ground/desert/smallblack.png (type=surface, x=0, y=0, width=75,  height=37); 
	rightsmallbrick = ../images/groundpieces/ground/desert/rightsmallbrick.png (type=surface, x=0, y=0, width=76,  height=38); 
	leftsmallbrick = ../images/groundpieces/ground/desert/leftsmallbrick.png (type=surface, x=0, y=0, width=76,  height=38); 
//	bigegpytpict = ../images/groundpieces/ground/desert/bigegyptpic.png (type=surface, x=0, y=0, width=386,  height=485); 
	mediumegpytpict = ../images/hotspots/desert/mediumegyptpic.png (type=surface, x=0, y=0, width=193,  height=242); 
	smallegpytpict = ../images/hotspots/desert/smallegyptpic.png (type=surface, x=0, y=0, width=96,  height=121); 
	bigdimwall = ../images/hotspots/desert/bigdimwall.png (type=surface, x=0, y=0, width=300,  height=150); 
	mediumdimwall = ../images/hotspots/desert/mediumdimwall.png (type=surface, x=0, y=0, width=150,  height=75); 
	smalldimwall = ../images/hotspots/desert/smalldimwall.png (type=surface, x=0, y=0, width=75,  height=37); 
	mediummummy = ../images/hotspots/desert/mediummummy.png (type=surface, x=0, y=0, width=133,  height=56); 
	mediumsphinx = ../images/hotspots/desert/mediumsphinx.png (type=surface, x=0, y=0, width=150,  height=97); 
	smallsphinx = ../images/hotspots/desert/smallsphinx.png (type=surface, x=0, y=0, width=75,  height=48); 
	smallD = ../images/hotspots/desert/smallD.png (type=surface, x=0, y=0, width=30,  height=38); 
	smallO = ../images/hotspots/desert/smallO.png (type=surface, x=0, y=0, width=30,  height=38); 
	smallG = ../images/hotspots/desert/smallG.png (type=surface, x=0, y=0, width=30,  height=38); 
	smallE = ../images/hotspots/desert/smallE.png (type=surface, x=0, y=0, width=30,  height=38); 

        // Pacman stuff
	pacmaze = ../images/special/pacman/pacman-maze.png (type=surface, x=0, y=0, width=453,  height=582); 
	pacframe = ../images/special/pacman/pacman-frame.png (type=surface, x=0, y=0, width=453,  height=582); 
	pacbkg = ../images/special/pacman/pacman-bkg.png (type=surface, x=0, y=0, width=453,  height=582); 
	pacman = ../images/special/pacman/pacman.png (type=surface, x=0, y=0, width=32,  height=32); 
	inky = ../images/special/pacman/inky.png (type=surface, x=0, y=0, width=32,  height=32); 
	blinky = ../images/special/pacman/blinky.png (type=surface, x=0, y=0, width=32,  height=32); 
	pinky = ../images/special/pacman/pinky.png (type=surface, x=0, y=0, width=32,  height=32); 
	clyde = ../images/special/pacman/clyde.png (type=surface, x=0, y=0, width=32,  height=32); 
}

section Steel
{
	steel1 = ../images/groundpieces/solid/industrial/steel1.png (type=surface, x=0, y=0, width=84, height=43);
	steel2 = ../images/groundpieces/solid/industrial/steel2.png (type=surface, x=0, y=0, width=44, height=167);
	steel3 = ../images/groundpieces/solid/industrial/steel3.png (type=surface, x=0, y=0, width=167, height=44);
	steel4 = ../images/groundpieces/solid/industrial/steel4.png (type=surface, x=0, y=0, width=233, height=105);
	steel5 = ../images/groundpieces/solid/industrial/steel5.png (type=surface, x=0, y=0, width=253, height=106);
}

section Gunge
{
	gunge1 = ../images/groundpieces/transparent/industrial/gunge1.png (type=surface, x=0, y=0, width=65, height=59);
	gunge10 = ../images/groundpieces/transparent/industrial/gunge10.png (type=surface, x=0, y=0, width=147, height=45);
	gunge11 = ../images/groundpieces/transparent/industrial/gunge11.png (type=surface, x=0, y=0, width=69, height=51);
	gunge12 = ../images/groundpieces/transparent/industrial/gunge12.png (type=surface, x=0, y=0, width=32, height=4);
	gunge13 = ../images/groundpieces/transparent/industrial/gunge13.png (type=surface, x=0, y=0, width=75, height=4);
	gunge14 = ../images/groundpieces/transparent/industrial/gunge14.png (type=surface, x=0, y=0, width=129, height=10);
	gunge15 = ../images/groundpieces/transparent/industrial/gunge15.png (type=surface, x=0, y=0, width=98, height=10);
	gunge2 = ../images/groundpieces/transparent/industrial/gunge2.png (type=surface, x=0, y=0, width=13, height=17);
	gunge3 = ../images/groundpieces/transparent/industrial/gunge3.png (type=surface, x=0, y=0, width=18, height=10);
	gunge4 = ../images/groundpieces/transparent/industrial/gunge4.png (type=surface, x=0, y=0, width=38, height=16);
	gunge5 = ../images/groundpieces/transparent/industrial/gunge5.png (type=surface, x=0, y=0, width=9, height=26);
	gunge6 = ../images/groundpieces/transparent/industrial/gunge6.png (type=surface, x=0, y=0, width=14, height=27);
	gunge7 = ../images/groundpieces/transparent/industrial/gunge7.png (type=surface, x=0, y=0, width=11, height=24);
	gunge8 = ../images/groundpieces/transparent/industrial/gunge8.png (type=surface, x=0, y=0, width=89, height=33);
	gunge9 = ../images/groundpieces/transparent/industrial/gunge9.png (type=surface, x=0, y=0, width=24, height=9);

}

section Buttons
{
        pause = ../images/core/buttons/pause.png (type=surface, x=0, y=0, width=38, height=56);
}

// EOF //
