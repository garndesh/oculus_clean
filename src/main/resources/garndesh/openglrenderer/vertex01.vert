#version 330 core

uniform mat4 m_proj;
uniform mat4 m_view;

layout(location = 0) in vec3 pos;
layout(location = 1) in vec2 tex;

out vec2 textureCoords;

void main() {
	textureCoords = tex;
	gl_Position = m_proj * m_view * vec4(pos, 1.0);
}