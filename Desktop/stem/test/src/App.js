import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box, Container, Grid, Paper } from '@mui/material';

const App = () => {
  return (
    <Box 
      sx={{ 
        minHeight: '100vh',
        display: 'flex',
        flexDirection: 'column',
        textAlign: 'center',
        px: 0.5, // Box의 좌우 패딩을 0.5로 설정하여 여백을 더 줄임
      }}
    >
      <AppBar position="static" sx={{ backgroundColor: '#ffffff', color: '#333', boxShadow: 1 }}>
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Typography variant="h6" sx={{ fontWeight: 'bold' }}>
            S.T.E.M
          </Typography>
          <Box sx={{ display: 'flex', gap: 2 }}>
            <Button color="inherit" href="#problem-recommend">문제 추천</Button>
            <Button color="inherit" href="#tech-notes">기술노트</Button>
            <Button color="inherit" href="#my-board">오답노트</Button>
            <Button color="inherit" href="#learning-status">학습현황</Button>
          </Box>
          <Button variant="contained" color="primary">
            로그인
          </Button>
        </Toolbar>
      </AppBar>
      
      <Container maxWidth="xl" sx={{ mt: 2, flexGrow: 2, px: 1 }}> {/* Container의 좌우 패딩을 1로 설정하여 여백을 줄임 */}
        <Typography variant="h4" sx={{ color: '#666', pt: 4 }}>
          단계별 추천학습
        </Typography>
        <Typography variant="h2" sx={{ color: '#333', mb: 'auto' }}>
          S.T.E.M
        </Typography>

        <Box sx={{ width: '100%', px: 0.5 }}> {/* Box의 좌우 패딩을 0.5로 설정하여 여백을 줄임 */}
          <Typography variant="h5" sx={{ textAlign: 'center', mb: 3 }}>
            추천 문제 TOP5
          </Typography>
          
          <Grid container spacing={2} justifyContent="center"> {/* Grid의 spacing을 2로 설정하여 여백을 줄임 */}
            {[1, 2, 3, 4, 5].map((item) => (
              <Grid item key={item} xs={12} sm={6} md={2.4}> {/* md를 2.4로 설정하여 카드 크기를 약간 넓힘 */}
                <Paper 
                  sx={{
                    height: 300, // 카드의 높이를 유지
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    backgroundColor: '#e0e0e0',
                    borderRadius: 2,
                    color: '#888',
                    fontSize: 18,
                    p: 1.5, // 카드 내부 패딩을 줄여 더 컴팩트하게 만듦
                  }}
                >
                  Card {item}
                </Paper>
              </Grid>
            ))}
          </Grid>
        </Box>
      </Container>
    </Box>
  );
}

export default App;
