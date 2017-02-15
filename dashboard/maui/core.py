import pygame

_context_stack=[]
ctx=None

def translate_event(e, by):
	if 'pos' in dir(e):
		return pygame.event.Event(e.type, pos=(e.pos[0]+by[0], e.pos[1]+by[1]), **{k:v for k,v in vars(e).items() if k!="pos"})
	return e

class MAUI:
	def __init__(self, size_or_surf, offset=(0,0)):
		self.size=size_or_surf.get_size() if isinstance(size_or_surf, pygame.Surface) else size_or_surf
		self.offset=offset
		self.master_clock=pygame.time.Clock()
		self.root=None
		self.running=False
		self.dt=1

	def handle_event(self, event):
		self.root.handle_event(translate_event(event, (-self.offset[0], -self.offset[1])))

	def handle_events(self, events):
		[self.handle_event(e) for e in events]

	def update(self):
		self.running=True
		global ctx

		self.dt=self.master_clock.tick()
		self.dt=1 if self.dt==0 else self.dt

		_context_stack.append(ctx)
		self.root.update()
		ctx=_context_stack.pop()

	def draw_to(self, surf, pos):
		self.root.draw_to_surface(surf, pos)

	def set_root(self, wid):
		self.root=wid
		wid.set_layout(*self.size)