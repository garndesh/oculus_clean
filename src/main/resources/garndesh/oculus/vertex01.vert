#version 330

uniform mat4 position = mat4(1);
//uniform mat4 global_position = mat4(1);
uniform mat4 modelview = mat4(1);

layout(location = 0) in vec3 Position;
layout(location = 1) in vec2 tex;

out vec2 textureCoords;

void main() {
	textureCoords = tex;
	gl_Position = position * modelview * vec4(Position, 1.0);
}