import pygame

def render_text(font, text, color, aa, bgcolor=None):
	lines=[font.render(line, aa, color, bgcolor) for line in text.split("\n")]
	line_height=lines[0].get_height()
	surf=pygame.Surface((max(line.get_width() for line in lines), line_height*len(lines)), pygame.SRCALPHA)
	for pos, line in enumerate(lines):
		surf.blit(line, (0, pos*line_height))
	return surf