import React from "react";
import PropTypes from "prop-types";
import {
  TableHead,
  TableRow,
  TableCell,
  Paper,
  TableContainer,
  Table,
  TableBody,
} from "@material-ui/core";

export default class PlayerStats extends React.Component {
  static propTypes = {
    stats: PropTypes.arrayOf(
      PropTypes.shape({
        nickname: PropTypes.string.isRequired,
        rank: PropTypes.number.isRequired,
        kills: PropTypes.number.isRequired,
        deaths: PropTypes.number.isRequired,
        totalMatches: PropTypes.number.isRequired,
        wonMatches: PropTypes.number.isRequired,
      })
    ),
    loadAllStats: PropTypes.func.isRequired,
  };

  componentDidMount() {
    this.props.loadAllStats();
  }

  render() {
    const { stats } = this.props;

    return (
      <TableContainer component={Paper}>
        <Table size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <TableCell align="center">Nickname</TableCell>
              <TableCell align="center">Rank</TableCell>
              <TableCell align="center">Kills</TableCell>
              <TableCell align="center">Deaths</TableCell>
              <TableCell align="center">Total matches</TableCell>
              <TableCell align="center">Won matches</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {stats.map((row) => (
              <TableRow key={row.name}>
                <TableCell align="center">{row.nickname}</TableCell>
                <TableCell align="center">{row.rank}</TableCell>
                <TableCell align="center">{row.kills}</TableCell>
                <TableCell align="center">{row.deaths}</TableCell>
                <TableCell align="center">{row.totalMatches}</TableCell>
                <TableCell align="center">{row.wonMatches}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
  }
}
