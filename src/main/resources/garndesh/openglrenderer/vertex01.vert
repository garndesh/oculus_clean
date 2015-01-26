#version 330
uniform mat4 Projection = mat4(1);
uniform mat4 ModelView = mat4(1);

layout(location = 0) in vec3 Position;
layout(location = 1) in vec2 tex;

out vec2 texCoords;

void main() {
	gl_Position = Projection * mat4(mat3(ModelView)) * vec4(Position, 1.0);
	texCoords = tex;
}