#version 330

uniform mat4 m_proj = mat4(1);
uniform mat4 m_view = mat4(1);

layout(location = 0) in vec3 Position;
layout(location = 1) in vec2 tex;

out vec2 textureCoords;

void main() {
	textureCoords = tex;
	gl_Position = m_proj * m_view * vec4(Position, 1.0);
}